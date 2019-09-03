pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.1' 
        jdk 'jdk8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Test') {
            steps {
                sh 'mvn test' 
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.skip=true install' 
            }
        }

        stage ('Deploy') {
            steps {
                sh 'cf push' 
            }
        }
    }
}