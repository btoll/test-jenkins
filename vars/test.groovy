// https://stackoverflow.com/a/54539868
def call(List<String> targets) {
    node {
        targets.each {
            stage (it) {
                echo it
                sh "ls -l"
            }
        }
    }
}

