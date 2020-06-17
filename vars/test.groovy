def call(List<String> targets) {
    withAWS(credentials: "aws-creds") {
        targets.each {
//            run(it)
            echo "${it}"
        }
    }
}

