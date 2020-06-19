def call(List<String> targets) {
    node {
        checkout scm

        docker.build("derp").inside("-u root") {
            targets.each {
                stage (it) {
                    sh "make ${it}"
                }
            }
        }
    }
}

