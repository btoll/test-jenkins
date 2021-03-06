import k8s.manifest.Pod

def call(List<String> targets) {
    podManifest = new Pod()

    targets.each {
        yaml = podManifest.getManifest("${it}")

        podTemplate(yaml: yaml) {
            node(POD_LABEL) {
                checkout scm

                container("make") {
                    targets.each {
    //                    stage(it) {
                            log.info "Testing on arch ${it}"
                            sh "make test"
    //                    }
                    }
                }
            }
        }
    }
}

