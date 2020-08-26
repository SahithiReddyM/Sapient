#Q1. maven command to display all goals
mvn help:describe -Dplugin=compiler
#Q2. leveraging local copy of maven within the project
mvn -Dmaven.repo.local=~/.m2/repository clean install
#Q3.to extract & save the build logs to a file instead of console output
mvn help:describe -Dplugin=compile --log-file log.txt
#Q4.to prevent maven from downloading dependencies every time / Make maven work without internet 
#Open a project that you want to build offline
mvn dependency:go-offline
mvn –o clean package
