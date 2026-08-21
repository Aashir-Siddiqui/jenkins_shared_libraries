def call(Map config = [:]) {
    def imageName = config.get('imageName', 'notes-app')
    def imageTag  = config.get('imageTag', 'latest')
    def gitBranch = config.get('gitBranch', 'main')
    def gitUrl    = config.get('gitUrl', 'https://github.com/Aashir-Siddiqui/django-notes-app.git')
    def credsId   = config.get('dockerCredsId', 'docker-hub-creds')

    pipeline {
        agent {
            label config.get('agentLabel', 'local-agent')
        }

        stages {
            stage('Clone Code') {
                steps {
                    clone(url: gitUrl, branch: gitBranch)
                }
            }

            stage('Build Image') {
                steps {
                    dockerBuild(imageName: imageName, imageTag: imageTag)
                }
            }

            stage('Push to Docker Hub') {
                steps {
                    dockerPush(imageName: imageName, imageTag: imageTag, credsId: credsId)
                }
            }

            stage('Deploy') {
                steps {
                    deploy()
                }
            }
        }

        post {
            always {
                echo 'Cleaning up dangling Docker images...'
                sh 'docker image prune -f'
            }
        }
    }
}
