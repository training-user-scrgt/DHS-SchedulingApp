pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                withMaven(maven : "name") {
                  sh 'mvn clean compile'
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
