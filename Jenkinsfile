pipeline {
    agent {
        label 'product-catalogue-agent'
    }

    stages {
        stage('Test Agent') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}