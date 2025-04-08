pipeline {
    agent any

    tools {
        maven '3.9.9'  // Make sure this version is configured in Jenkins
    }

    environment {
        DOCKERHUB_USER = 'mahesh946'
        IMAGE_NAME = "${DOCKERHUB_USER}/teamravanan-application-mangodb"
        CONTAINER_NAME = "teamravanan-application-mangodb"
        APP_PORT = "2020"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Maheshkumar1510/team-ravanan.git'
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Docker Hub Login') {
            steps {
               withCredentials([string(credentialsId: 'dockerId', variable: 'dockerPwd')]) {
                   bat "docker login -u mahesh946 -p ${dockerPwd}"
               }
            }
        }
        stage('Docker Push') {
                    steps {
                        bat "docker push mahesh946/teamravanan-application-mangodb"
                    }
                }

        stage('Deploy Container') {
            steps {
                bat '''
                docker stop %CONTAINER_NAME%
                docker rm %CONTAINER_NAME%
                docker run -d --name %CONTAINER_NAME% -p %APP_PORT%:%APP_PORT% %IMAGE_NAME%
                '''
            }
        }
    }
}
