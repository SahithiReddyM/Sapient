pipeline {
    agent any 
	triggers{
		cron('H 10 * * *')
	}
    stages {
		stage("SCM checkout")   {
			steps {
				git 'https://github.com/SahithiReddyM/Pipeline_trigger.git'
			}
	    } 
        stage ('----clean----') {
            steps {
                bat "mvn clean"
            }
        }

        stage ('----test----')  {
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
				dir('target')
				{
					bat "java -jar my-app-1.0-SNAPSHOT.jar"
				}
			}
		}
	}
}