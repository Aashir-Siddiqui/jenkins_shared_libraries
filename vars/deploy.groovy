def call() {
    echo "Deploying fresh containers via Docker Compose..."
    sh "docker-compose down && docker-compose up -d"
}
