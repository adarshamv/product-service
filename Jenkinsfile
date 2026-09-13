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

        stage('Build & Push Image') {
            steps {
                container('kaniko') {
                    sh '''
                        /kaniko/executor \
                          --context="${WORKSPACE}" \
                          --dockerfile="${WORKSPACE}/Dockerfile" \
                          --destination="YOUR_ECR_REPOSITORY:${BUILD_NUMBER}"
                    '''
                }
            }
        }

        stage('Deploy Staging') {
            steps {
                // Your Kubernetes deployment commands go here
            }
        }

        stage('Staging Smoke Test') {
            steps {
                // Your /actuator/health check goes here
            }
        }

        stage('Production Approval') {
            steps {
                input message: 'Deploy to production?', ok: 'Deploy'
            }
        }

        stage('Deploy Production') {
            steps {
                // Your production deployment commands go here
            }
        }
    }
}