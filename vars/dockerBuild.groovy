def call(Map config = [:]) {
    def imageName = config.get('imageName', 'notes-app')
    def tagName = config.get('tagName', 'latest')

    echo "Building Docker Image: ${imageName}:${tagName}..."
    sh "docker build -t ${imageName}:${tagName} ."
}
