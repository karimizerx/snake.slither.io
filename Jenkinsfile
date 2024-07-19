pipeline {
    agent any // Exécute le pipeline sur n'importe quel agent disponible

    stages {
        stage('Build') { // Étape de construction
            steps {
                echo 'Building..'
                ./gradlew build
            }
        }
        stage('Test') { // Étape de test
            steps {
                echo 'Testing..'
                ./gradlew test
            }
        }
        stage('Deploy') { // Étape de déploiement
            steps {
                echo 'Deploying..'
                echo "Deploye succeed"
            }
        }
    }
}