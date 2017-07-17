package com.hitrate.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	private DBConnection() { }
	
	public static Connection getConnection(String dbUrl, String dbUsername, String dbPassword) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		return connection;
	}
}
