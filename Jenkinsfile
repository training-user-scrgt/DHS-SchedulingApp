pipeline {
    agent any

    stage {
        stage('Stage') {
             stage('Build') {
                    steps {
                        echo 'Building..'
                        
                    }
            }
        
            stage('OWASP Scan') {
                    steps {
                        echo 'Building..'
                        
                    }
            }
            stage('Code Scan') {
                    steps {
                        echo 'Building..'
                        
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
