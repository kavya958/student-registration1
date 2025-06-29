pipeline {
    agent any

   environment {
    IMAGE_NAME = 'kavya958/student-registration'
    DOCKER_CREDENTIALS_ID = 'docker-hub-creds' // (keep same if you already added this credential in Jenkins)
}

    stages {
        stage('Clone') {
            steps {
                git url :'https://github.com/kavya958/student-registration1', branch: 'main'

            }
        }

        stage('Build') {
            steps {
                bat 'mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
            post{
                always{
                    junit '**/target/surefire-reports/*.xml'
                }
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
