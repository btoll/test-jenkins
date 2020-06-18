def call(List<String> targets) {
    withAWS(credentials: "aws-creds") {
        node {
            targets.each {
                run(it)
            }
        }
    }
}

