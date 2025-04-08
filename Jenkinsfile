pipeline {
    agent any

    tools {
        maven '3.9.9'
    }

    environment {
        DOCKERHUB_USER = 'mahesh946'  // 👈 change this
        IMAGE_NAME = "${DOCKERHUB_USER}/teamravanan-application-mangodb"
        CONTAINER_NAME = "teamRavanan-application-mangodb"
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
                bat 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: '1c892169c-4402-4820-a65a-dc777029b834', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $IMAGE_NAME
                    '''
                }
            }
        }

        stage('Deploy Container') {
            steps {
                bat '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                docker run -d --name $CONTAINER_NAME -p 2020:2020 $IMAGE_NAME
                '''
            }
        }
    }
}
