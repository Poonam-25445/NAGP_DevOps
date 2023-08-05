pipeline {
    agent any

    environment{
       HOME = "."
    }
    
    options{
         skipDefaultCheckout()
         buildDiscarder(logRotator(daysToKeepStr: '', numToKeepStr: '10'))
         timeout(time: 180, unit: 'MINUTES')
    }

    stages {
         stage('Clone Repository') {
            steps {
                // Clean workspace before cloning
                cleanWs()
                
                // Clone the repository
                git branch: 'main', url: 'https://github.com/Poonam-25445/NAGP_DevOps.git'
            }
         }
            
        stage('Build') {
            steps {

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean install -DskipTests"
            }

        }
        
        stage('Run Selenium Tests') {
            steps {
                bat "mvn test -Dmaven.test.failure.ignore=true"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}