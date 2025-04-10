pipeline {
    agent any

    tools {
        maven '3.9.9'  // Ensure Maven 3.9.9 is configured in Jenkins Global Tool Configuration
    }

    environment {
        DOCKERHUB_USER   = 'mahesh946'
        IMAGE_NAME       = "${DOCKERHUB_USER}/teamravanan-application-mangodb"
        CONTAINER_NAME   = "teamravanan-application-mangodb"
        APP_PORT         = "2020"
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
        stage('SonarQube Analysis') {
             steps {
                  withSonarQubeEnv('MySonarQube') {
                   bat 'mvn sonar:sonar -Dsonar.projectKey=team-ravanan -Dsonar.projectName=TeamRavananApp'
                        }
                    }
                }
        stage('Quality Gate') {
                    steps {
                        timeout(time: 1, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                        }
                    }
                }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME%:%BUILD_NUMBER% ."
            }
        }

        stage('Docker Hub Login') {
            steps {
                withCredentials([string(credentialsId: 'dockerId', variable: 'dockerPwd')]) {
                    bat "docker login -u mahesh946 -p %dockerPwd%"
                }
            }
        }

        stage('Docker Push') {
            steps {
                bat "docker push %IMAGE_NAME%:%BUILD_NUMBER%"
            }
        }

        stage('Deploy Container') {
            steps {
                bat '''
                    docker stop %CONTAINER_NAME% || exit 0
                    docker rm %CONTAINER_NAME% || exit 0
                    docker run -d --name %CONTAINER_NAME% -p %APP_PORT%:%APP_PORT% %IMAGE_NAME%:%BUILD_NUMBER%
                '''
            }
        }
    }
}