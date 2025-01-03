pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9' // Ensure Maven is configured in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonarcloud-token') // Add your SonarCloud token in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/AbhishekHerbertSamuel/maven-sonarqube.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') { // Ensure SonarCloud is configured in Jenkins
                    sh 'mvn sonar:sonar -Dsonar.projectKey=maven-sonarqube -Dsonar.organization=abhishekherbertsamuel'
                }
            }
        }
    }
}

