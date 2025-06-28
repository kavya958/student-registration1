pipeline {
    agent any

    environment {
        IMAGE_NAME = 'yourdockerhubusername/student-registration'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
    }

    stages {
        stage('Clone') {
            steps {
                git url :'https://github.com/saipriya3008/studentRegistration', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        def image = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                        image.push()
                    }
                }
            }
        }
    }

    post {
        success {
            echo ' Build & Push Successful'
        }
        failure {
            echo ' Pipeline Failed'
        }
    }
}
