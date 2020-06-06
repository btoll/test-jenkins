// https://stackoverflow.com/a/54539868
def call(List<String> targets) {
    pipeline {
        node {
            targets.each {
                stage (it) {
                    echo it
                    sh "ls -l"
                }
            }
        }
    }
}

