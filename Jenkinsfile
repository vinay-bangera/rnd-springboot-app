pipeline {

    agent none

    stages {
        stage ('Build')  {
            agent {
                docker { image 'maven:3.8.2-jdk-11'}
            }
            steps {
                sh 'mvn clean install'
            }
        }
        stage ('Build Docker image')  {
            label 'ansible'
            steps {
                docker.build("MyImage:latest" "-f Dockerfile")
            }
        }

    }
}