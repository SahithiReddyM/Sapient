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
				bat "java -jar target\*.jar"
			}
		}
    }
}