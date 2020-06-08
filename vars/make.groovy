// https://stackoverflow.com/a/54539868
def call(List<String> targets) {
    node {
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

