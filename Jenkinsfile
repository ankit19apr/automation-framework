pipeline {
    agent any

    environment {
        BROWSER = 'chrome'
        IS_LAMBDA_TEST = 'true'
        IS_HEADLESS = 'false'
    }

    triggers {
        // Trigger the build based on the schedule (e.g., 03:30 AM daily)
        cron('30 3 * * *')
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://your-repository-url.git'
            }
        }

        stage('Set up JDK 11') {
            tools {
                jdk 'JDK 11' // Ensure "JDK 11" is configured in Global Tool Configuration
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    // Run Maven tests with specific parameters
                    sh 'mvn test -X "-Dbrowser=${BROWSER}" "-DisLambdaTest=${IS_LAMBDA_TEST}" "-DisHeadless=${IS_HEADLESS}"'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                // Archive logs, screenshots, and reports
                archiveArtifacts artifacts: 'logs/**, screenshots/**, report.html', fingerprint: true
            }
        }

        stage('Test Execution Status') {
            steps {
                echo 'Test Execution Completed'
            }
        }

        stage('Prepare Reports for Deployment') {
            steps {
                script {
                    // Copy reports and screenshots to a folder for deployment
                    sh '''
                        mkdir -p public/extent-reports
                        cp report.html public/extent-reports/
                        cp -R screenshots/ public/extent-reports/screenshots/
                        ls -l public/extent-reports
                    '''
                }
            }
        }

        stage('Deploy to GitHub Pages') {
            steps {
                script {
                    // Deploy artifacts to GitHub Pages (you can use a custom deployment script)
                    withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                        sh '''
                            git config user.name "Jenkins"
                            git config user.email "jenkins@example.com"
                            cd public/extent-reports
                            git init
                            git add .
                            git commit -m "Deploy extent reports"
                            git push --force https://$GITHUB_TOKEN@github.com/your-repository-url.git master:gh-pages
                        '''
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
    }
}
