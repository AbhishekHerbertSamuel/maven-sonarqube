pipeline {
    agent any
    tools {
        maven 'Maven_3.9.9' // Ensure this matches the Jenkins Maven tool configuration
    }
    environment {
        SONAR_TOKEN = credentials('sonarcloud-token') // Ensure this matches your Jenkins credential ID
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/AbhishekHerbertSamuel/maven-sonarqube.git'
            }
        }
        stage('Maven Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('SonarCloud Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.organization=abhishekherbertsamuel \
                        -Dsonar.projectKey=maven-sonarqube \
                        -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}


