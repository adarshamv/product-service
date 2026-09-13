pipeline {
    agent {
        label 'product-catalogue-agent'
    }

    stages {
        stage('Test Agent') {
            steps {
                container('maven') {
                    sh 'mvn --version'
                }
            }
        }
    }
}