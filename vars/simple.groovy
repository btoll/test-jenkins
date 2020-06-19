import k8s.manifest.Pod

def make(List<String> targets) {
    podManifest = new Pod()
    yaml = podManifest.getManifest("amd64")

    podTemplate(yaml: yaml) {
        node(POD_LABEL) {
            checkout scm

            container("make") {
                targets.each {
                    stage (it) {
                        log.info "Running make target ${it}"
                        sh "make ${it}"
                    }
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

                container("make") {
                    stage ("make test -> ${it}") {
                        log.info "Testing on arch ${it}"
                        sh "make test"
                    }
                }
            }
        }
    }
}

