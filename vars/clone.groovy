def call(Map config = [:] ) {
  def branch = config.get('branch', 'main')
  def url = config.get('url', 'https://github.com/Aashir-Siddiqui/django-notes-app.git')
  echo "Cloning repository: ${url} (Branch: ${branch})..."
  git branch: branch, url: url
}
