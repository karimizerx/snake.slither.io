pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'make build'
            }
        }
        stage('Test'){
            steps {
                echo 'Testing..'
                sh 'make test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying..'
                sh 'make javadoc'
            }
        }
    }
}