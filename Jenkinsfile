pipeline {
    agent {
        label 'product-catalogue-agent'
    }

    stages {
        stage('Unit Tests') {
            steps {
                container('maven') {
                    sh 'mvn test'
                }
            }
        }
    }
}