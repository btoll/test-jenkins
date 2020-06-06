// https://stackoverflow.com/a/54539868
def call(List<String> targets) {
    node {
        git branch: 'derp', url: 'https://github.com/algorand/indexer.git'

        targets.each {
            stage (it) {
                echo it
                sh "make ${it}"
            }
        }
    }
}

