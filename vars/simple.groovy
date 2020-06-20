import k8s.manifest.Pod

def getProjectName() {
    // Returns the project/repo name, i.e.:
    // https://github.com/algorand/indexer -> indexer
    String url = scm.getUserRemoteConfigs()[0].getUrl()
    return url.split("/")[-1]
}

def make(List<String> targets) {
    String project = getProjectName()
    Pod podManifest = new Pod(name: project)

    docker.image("btoll/${project}_amd64:latest").inside("-u root") {
        node(POD_LABEL) {
            checkout scm

            targets.each {
                stage (it) {
                    log.info "Running make target ${it}"
                    sh "make ${it}"
                }
            }
        }
    }
}

def test(List<String> archs) {
    String project = getProjectName()
    Pod podManifest = new Pod(name: project)
    def pods = [:]

    archs.each {
        String yaml = podManifest.getManifest(it)

        pods[it] = {
            podTemplate(yaml: yaml) {
                node(POD_LABEL) {
                    checkout scm

                    container("${project}-${it}") {
                        stage ("make test -> ${it}") {
                            log.info "Testing on arch ${it}"
                            sh "make test"
                        }
                    }
                }
            }
        }
    }

    parallel pods
}

