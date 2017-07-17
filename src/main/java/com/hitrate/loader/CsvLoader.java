package com.hitrate.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVReader;

public class CsvLoader {
	public static final String CLEAN_TMP_TABLE_SQL = "DELETE FROM hitrate_tmp";
	public static final String DELETE_DUPLICATE_SQL = "DELETE FROM hitrate WHERE EXISTS (SELECT 1 FROM hitrate_tmp where hitrate_tmp.visit_date = hitrate.visit_date and hitrate_tmp.website = hitrate.website)";
	public static final String INSERT_DATA_SQL = "INSERT INTO hitrate (visit_date, website, visits) SELECT visit_date, website, visits FROM hitrate_tmp";
	public static final int VISIT_DATE_INDEX = 1;
	public static final int WEBSITE_INDEX = 2;
	public static final int VISITS_INDEX = 3;
	public static final int BATCH_SIZE = 30;
	
	private File file;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;

	public static void main(String[] args) {
		if (args[0] != null) {
			try {
				CsvLoader csvLoader = new CsvLoader(args[0]);
				csvLoader.process();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public CsvLoader(String env) throws IOException {
		loadProperties(env);
	}

	public void process() throws SQLException {
		Path myDir = Paths.get(file.getParent());

		try {
			WatchService watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

			WatchKey watckKey = watcher.take();

			List<WatchEvent<?>> events = watckKey.pollEvents();
			for (WatchEvent<?> event : events) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					if (loadCsv()) {
						moveFileToProcessed();
					} else {
						System.out.println("Failed to load CSV file");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	private void loadProperties(String env) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-" + env + ".properties");

		if (inputStream != null) {
			properties.load(inputStream);
			file = new File(properties.getProperty("file"));
			dbUrl = properties.getProperty("db.url");
			dbUsername = properties.getProperty("db.username");
			dbPassword = properties.getProperty("db.password");
		} else {
			throw new FileNotFoundException(
					"Properties file application-" + env + ".properties not found in classpath");
		}
	}

	@SuppressWarnings("resource")
	private boolean loadCsv() throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getConnection(dbUrl, dbUsername, dbPassword);
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			stmt.execute(CLEAN_TMP_TABLE_SQL);

			loadDataToTmpTable(connection);

			stmt = connection.createStatement();
			stmt.execute(DELETE_DUPLICATE_SQL);

			stmt = connection.createStatement();
			stmt.execute(INSERT_DATA_SQL);

			System.out.println("File is loaded to database");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			connection.setAutoCommit(true);
		}
	}

	private void moveFileToProcessed() {
		if (file.renameTo(new File(file.getParent() + "/processed/" + file.getName()))) {
			System.out.println("File is moved to processed successfully!");
		} else {
			System.out.println("Failed to move file!");
		}
	}

	private void loadDataToTmpTable(Connection connection) throws IOException, SQLException, ParseException {
		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(file.getPath()), '|', '\'', 1);
		String insertQuery = "Insert into hitrate_tmp (visit_date, website, visits) values (?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insertQuery);
		String[] rowData = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		while ((rowData = reader.readNext()) != null) {
			pstmt.setDate(VISIT_DATE_INDEX, new java.sql.Date(formatter.parse(rowData[0]).getTime()));
			pstmt.setString(WEBSITE_INDEX, rowData[1]);
			pstmt.setInt(VISITS_INDEX, Integer.parseInt(rowData[2]));
			pstmt.addBatch(); // add batch

			if (i % BATCH_SIZE == 0) {
				pstmt.executeBatch();
			}
		}
		System.out.println("Data Successfully Uploaded");
	}
}
