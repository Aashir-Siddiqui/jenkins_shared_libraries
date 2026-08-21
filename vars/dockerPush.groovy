def call(Map config = [:]) {
    def imageName = config.get('imageName', 'notes-app')
    def imageTag = config.get('imageTag', 'latest')
    def credsId   = config.get('credsId', 'docker-hub-creds')

    echo "Pushing ${imageName} to Docker Hub..."
    withCredentials([usernamePassword(
        credentialsId: credsId,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh """
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker tag ${imageName}:${imageTag} \$DOCKER_USER/${imageName}:${imageTag}
            docker push \$DOCKER_USER/${imageName}:${imageTag}
        """
    }
}
