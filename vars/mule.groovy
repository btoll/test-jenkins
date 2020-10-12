def call(String recipe) {
    node {
        checkout scm

        docker.build("mule-test", "-f ./docker/build/docker.ubuntu.Dockerfile .").inside("-u root") {
            sh "mule -r ${recipe}"
        }
    }
}

