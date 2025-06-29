pipeline {
    agent any

    environment {
        IMAGE_NAME = 'kavya958/student-registration'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds' // Create this in Jenkins > Credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/kavya958/student-registration1.git', branch: 'main'
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
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        def appImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                        appImage.push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo '✅ Kubernetes deployment goes here (can be skipped if not configured)'
                // You can put your kubectl or helm deployment command here
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, Docker Push successful!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}


