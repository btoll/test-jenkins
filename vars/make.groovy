// https://stackoverflow.com/a/54539868
def call(List<String> targets) {
    node {
//        git branch: 'derp', url: 'https://github.com/algorand/indexer.git'
        checkout scm

        docker.build('foo').inside('-u root') {
            targets.each {
                stage (it) {
                    sh "make ${it}"
                }
            }
        }
    }
}

