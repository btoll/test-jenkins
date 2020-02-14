def CHANNEL = "moo"
pipeline {
    agent any

    stages {
        stage("foo") {
            steps {
                /*
                sh 'printenv'
                */
                sh "echo ${env.GIT_BRANCH} ${CHANNEL}"
            }
        }
    }
}

