def call(String mule_version = "latest") {
    // https://algorand-releases.s3.amazonaws.com/recipe/beta/2.4.0/package-amd64.recipe
    String recipe_location = env.getProperty("RECIPE")
    String s3_location = "https://algorand-releases.s3.amazonaws.com/recipe/${recipe_location}"

    def pods = [:]

    ["amd64", "arm64v8", "arm32v6"].each {
        pods[it] = {
            podTemplate(yaml: pod(mule_version, "package-build-pipeline", it)) {
                node(POD_LABEL) {
                    checkout scm

                    content = new URL("https://algorand-releases.s3.amazonaws.com/recipe/${recipe_location}/${it}").getText()

                    stage ("Package ${it}") {
                        container("mule") {
                            sh "echo \"${content}\" | mule -r -"
                        }
                    }
                }
            }
        }
    }

    parallel pods
}

