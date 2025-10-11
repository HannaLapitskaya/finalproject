pipeline {
    agent any
    tools {
        maven 'Maven 3.9.11'
    }

    stages {
        stage('Tests') {
            steps {
                bat "mvn clean test"
            }
        }
    }

    post {
        always {
            publishHTML([
                target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity Report'
                ]
            ])
        }
        failure {
            echo 'Tests failed'
        }
        success {
            echo 'All tests passed'
        }
    }
}