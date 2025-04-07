pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    environment {
        DOCKERHUB_USER = 'mahesh946'  // 👈 change this
        IMAGE_NAME = "${DOCKERHUB_USER}/teamRavanan-application-mangodb"
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: '10bcd8cd-ac81-49c6-8990-29579a5e78e5', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $IMAGE_NAME
                    '''
                }
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                docker run -d --name $CONTAINER_NAME -p 2020:2020 $IMAGE_NAME
                '''
            }
        }
    }
}
