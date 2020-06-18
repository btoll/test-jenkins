def call(List<String> targets) {
    targets.each {
        stage (it) {
            agent {
                kubernetes "foo"
            }
            sh "make ${it}"
        }
    }

    pipeline {
        agent any

        stages {
            stage('foo') {
                steps {
                script {
                    targets.each {
                        stage (it) {
                            agent {
                                kubernetes "foo"
                            }
                            sh "make ${it}"
                        }
                    }
                }
                }
            }
        }
    }
}

