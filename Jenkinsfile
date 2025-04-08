pipeline {
    agent any

    tools {
        maven '3.9.9'  // Make sure this Maven version is configured in Jenkins tools
    }

    environment {
        DOCKERHUB_USER = 'mahesh946'  // 👈 Replace with your actual DockerHub username
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
                bat '''
                echo "Building Docker Image: $IMAGE_NAME"
                docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: '1c892169c-4402-4820-a65a-dc777029b834',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
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
                echo "Stopping existing container if any..."
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true

                echo "Starting new container..."
                docker run -d --name $CONTAINER_NAME -p $APP_PORT:$APP_PORT $IMAGE_NAME
                '''
            }
        }
    }
}
