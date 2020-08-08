def call(List<String> targets, String muleYaml="mule.yaml") {
    node {
        checkout scm

        docker.build("derp", "-f ./docker/cicd/Dockerfile .").inside("-u root") {
            targets.each {
                stage (it) {
                    if (it == "sign") {
                        input(message: "Forward gpg-agent")
                    }

                    sh "mule -f ${muleYaml} ${it}"
                }
            }
        }
    }
}

