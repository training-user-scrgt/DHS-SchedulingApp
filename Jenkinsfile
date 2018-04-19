pipeline {
    agent any

    stages {
        stage('Stage') {
             stage('Build') {
                    steps {
                        echo 'Building..'
                        withMaven(maven : "name") {
                          sh 'mvn clean compile'
                          }
                    }
            }
        
            stage('OWASP Scan') {
                    steps {
                        echo 'Building..'
                        withMaven(maven : "name") {
                          sh 'mvn clean compile'
                          }
                    }
            }
            stage('Code Scan') {
                    steps {
                        echo 'Building..'
                        withMaven(maven : "name") {
                          sh 'mvn clean compile'
                          }
                    }
            }
        
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                withMaven(maven : "name") {
                  sh 'mvn test'
                  }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                withMaven(maven : "name") {
                  sh 'mvn repository'
                  }
            }
        }
    }
}
