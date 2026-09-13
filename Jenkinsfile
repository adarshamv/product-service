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
                              --destination="379063509266.dkr.ecr.ap-south-1.amazonaws.com/dev/app:${BUILD_NUMBER}"
                        '''
                    }
                }
            }

            stage('Deploy Staging') {
                steps {
                    container('kubectl') {
                        sh '''
                            kubectl set image deployment/product-catalogue \
                              product-catalogue=379063509266.dkr.ecr.ap-south-1.amazonaws.com/dev/app:${BUILD_NUMBER} \
                              -n staging

                            kubectl rollout status deployment/product-catalogue \
                              -n staging \
                              --timeout=5m
                        '''
                    }
                }
            }
//
//         stage('Staging Smoke Test') {
//             steps {
//                 // Your /actuator/health check goes here
//             }
//         }
//
//         stage('Production Approval') {
//             steps {
//                 input message: 'Deploy to production?', ok: 'Deploy'
//             }
//         }
//
//         stage('Deploy Production') {
//             steps {
//                 // Your production deployment commands go here
//             }
//         }
    }
}