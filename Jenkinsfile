pipeline {
    agent any

    triggers {
        cron('30 03 * * *') // Schedule build at 3:30 AM daily
        pollSCM('H/5 * * * *') // Optional: Polls SCM every 5 minutes for changes
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Set up JDK 11') {
            steps {
                echo 'Setting up JDK 11...'
                withEnv(['JAVA_HOME=/usr/lib/jvm/java-11-openjdk', 'PATH+JAVA=$JAVA_HOME/bin']) {
                    sh 'java -version'
                }
            }
        }

        stage('Run Maven Tests') {
            steps {
                echo 'Running Test Automation Framework with Maven...'
                sh 'mvn test -X -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false'
            }
        }

        stage('Archive Test Logs') {
            steps {
                echo 'Uploading Test Logs...'
                archiveArtifacts artifacts: 'logs/**', allowEmptyArchive: true
            }
        }

       

        stage('Archive HTML Report') {
            steps {
                echo 'Uploading HTML Report...'
                archiveArtifacts artifacts: 'report.html', allowEmptyArchive: true
            }
        }

        stage('Copy Reports to Public Folder') {
            steps {
                echo 'Copying reports to public folder...'
                script {
                    sh '''
                        mkdir -p public/extent-reports
                        cp report.html public/extent-reports/
                     
                        ls -l public/extent-reports
                    '''
                }
            }
        }

        stage('Deploy to GitHub Pages') {
            steps {
                echo 'Deploying reports to GitHub Pages...'
                withCredentials([string(credentialsId: 'PersonalAccessToken', variable: 'GITHUB_TOKEN')]) {
                    sh '''
                        git config --global user.name "Jenkins CI"
                        git config --global user.email "ci@jenkins.com"
                        git clone --branch=gh-pages https://github.com/ankit19apr/automation-framework.git gh-pages
                        cd gh-pages
                        mkdir -p extent-reports
                        cp -R ../public/extent-reports/* extent-reports/
                        git add .
                        git commit -m "Deploy test reports"
                        git push origin gh-pages
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Test Execution Completed'
        }
    }
}
