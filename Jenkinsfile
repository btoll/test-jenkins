pipeline {
    /*
    parameters {
        string defaultValue: 'master', description: 'Branch name or tag name.', name: 'branch', trim: true
        string defaultValue: 'stable', description: 'Staged channel which should be released.', name: 'channel', trim: true
        string defaultValue: 'algorand-builds/channel', description: 's3://bucket/prefix', name: 'bucket_location', trim: true

        // AWS
        string defaultValue: 'us-west-1', description: 'AWS Region', name: 'region', trim: true
        string defaultValue: 'ami-0dd655843c87b6930', description: 'Amazon Machine Image (default: Ubuntu Server 18.04 LTS, 8 vCPUs, 32 GB RAM', name: 'ami', trim: true
        string defaultValue: 't2.2xlarge', description: 'Instance Type', name: 'type', trim: true
    }

    environment {
        AWS_ACCESS_KEY_ID = credentials("prod-s3-aws-access-key-id")
        AWS_SECRET_ACCESS_KEY = credentials("prod-s3-aws-secret-access-key")
    }
    */

    agent any

    stages {
        stage("foo") {
            steps {
                input "This is a foo!!"
            }
        }
    }
}

