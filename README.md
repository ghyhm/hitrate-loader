# hitrate-loader
## Mechanism
The location of file to watch for is defined in application configuration file.

The jar file should be executed in the background by a shell script.

We can have a scheduler like Autosys so that the program is executed when file exists.

Once the file is loaded, it will be moved to "processed" folder.

It is a standalone program and not bundled to the hitrate report web application. It can run even when the web application is down.

We need to check if file format is valid like the column type, number of columns etc.

Email notification needs to be sent in case any failure.

Since processed file will be put to processed folder, regular clean up job is required to remove obsolete files so that we only keep history of files to a certain period only.

## Application configuration
Application configuration files are defined in hitrate-loader/src/main/resources/properties.

application-dev.properties and application-prd.properties correspond to development and production environments respectively.

When executing the jar, it looks at the environment variable (dev or prd) to determine which properties file to use.

## Build the jar
Run below command to create the jar file.

<code>gradle createJar</code>

## How to run
1. Update properties

Properties files are in /hitrate-loader/src/main/resources

2. Run the jar in background

<code>java -jar build/libs/hitrate-loader-1.jar dev &</code>

The parameter is to specify whether we use dev or prd properties file.

## Database
MySQL is used in the application.

It is assumed that the 2 tables: hitrate and hitrate_tmp are already created in the hitrate-report project.

## Checkstyle for code analysis
Checkstyle is used for static code analysis. This is to check coding standard.

Run below command to check the code.

<code>gradle check</code>

## Cucumber for BDD
Cucumber is setup for BDD.

Features are defined in src/test/resources.

Run below command to execute the features.

<code>gradle cucumber</code>

Note: 

1. Actual implemention on test step is not done yet. We need to first set up a test database which will run the same database 
migration scripts. The test database will be setup before tests start and will be torn down after running the tests.

## Integration with Circle CI

The project is integrated with Circle CI. Any check-in to github will run circle ci automatically.

We can define the task to run in circle CI, like running all cucumber tests.

<a href="https://circleci.com/gh/ghyhm/hitrate-loader">Hit Rate Loader Circle CI</a>

## Assumptions
1. csv file should follow defined format.

2. New data always overrides existing data if the same website and visit date already exists in DB.
