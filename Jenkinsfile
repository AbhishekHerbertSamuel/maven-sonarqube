pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven_3.9.9' // Ensure Maven is configured in Jenkins
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
        SONAR_TOKEN = credentials('sonarcloud-token') // Replace with your Jenkins credentials ID
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

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                // Inject SonarQube environment variables
                SONAR_HOST_URL = 'https://sonarcloud.io'
            }
            steps {
                withSonarQubeEnv('SonarCloud') { // Configure SonarQube in Jenkins
                    sh '''
                    mvn sonar:sonar \
                        -Dsonar.organization=abhishekherbertsamuel \
                        -Dsonar.projectKey=maven-sonarqube \
                        -Dsonar.login=$SONAR_TOKEN
                    '''
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



