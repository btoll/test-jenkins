//def call(Map config) {
def call(String target) {
    node {
        docker.build("foo").inside("-u root") {
            log.info "Running ${target}"
            sh "make ${target}"
        }
    }
}

