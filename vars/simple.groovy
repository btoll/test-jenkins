import k8s.manifest.Pod

def make(List<String> targets) {
    podManifest = new Pod(name: "indexer")
    yaml = podManifest.getManifest("amd64")

    podTemplate(yaml: yaml) {
        node(POD_LABEL) {
            checkout scm

            container("indexer") {
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
def test(List<String> archs) {
    podManifest = new Pod(name: "indexer")

    archs.each {
        yaml = podManifest.getManifest(it)

        podTemplate(yaml: yaml) {
            node(POD_LABEL) {
                checkout scm

                container("indexer") {
                    stage ("make test -> ${it}") {
                        log.info "Testing on arch ${it}"
                        sh "make test"
                    }
                }
            }
        }
    }
}

