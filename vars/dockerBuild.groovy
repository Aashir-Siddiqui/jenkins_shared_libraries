def call(Map config = [:]) {
    def imageName = config.get('imageName', 'notes-app')
    def imageTag = config.get('imageTag', 'latest')

    echo "Building Docker Image: ${imageName}:${imageTag}..."
    sh "docker build -t ${imageName}:${imageTag} ."
}
