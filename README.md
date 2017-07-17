# hitrate-loader
## Build the jar
<code>gradle createJar</code>

## How to run
1. Update properties

Properties files are in /hitrate-loader/src/main/resources

application-dev.properties and application-prd.propertes correspond to development and production environments respectively.

2. Run the jar in background

<code>java -jar build/libs/hitrate-loader-1.jar dev &</code>

The parameter is to specify whether we use dev or prd properties file.

In real world, we can have shell scripts to execute the jar file. Then we can setup schedular like Autosys to run the shell script when the file exists.

## Assumptions
1. csv file should follow defined format.

2. New data always overrides existing data if the same website and visit date already exists in DB.
