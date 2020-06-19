import k8s.manifest.Pod

def make(List<String> targets) {
    podManifest = new Pod()
    yaml = podManifest.getManifest("amd64")

    podTemplate(yaml: yaml) {
        node(POD_LABEL) {
            checkout scm

            container("mule") {
                targets.each {
                    log.info "Running make target ${it}"
                    sh "mule -f mule.yaml ${it}"
                }
            }
        }
    }
}

// TODO: Run these in parallel.
def test(List<String> targets) {
    podManifest = new Pod()

    targets.each {
        yaml = podManifest.getManifest(it)

        podTemplate(yaml: yaml) {
            node(POD_LABEL) {
                checkout scm

                container("mule") {
                    log.info "Testing on arch ${it}"
                    sh "mule -f mule.yaml test"
                }
            }
        }
    }
}

