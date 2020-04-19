pipeline {
    agent any
    stages {
        stage ('----clean----') {
            steps {
                bat "mvn clean"
            }
        }

        stage ('----test----') {
            steps {
                bat "mvn test"
            }
        }
		stage('----package----'){
			steps{
				bat "mvn package"
			}
		}
		stage('----execute----'){
			steps{
				bat " mvn exec:java -Dexec.mainClass='course.maven.quick.App' -s 'C:\DevTools\apache-maven-3.6.3-bin\apache-maven-3.6.3\conf\settings.xml'"
			}
		}
    }
}