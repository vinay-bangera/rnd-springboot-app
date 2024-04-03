pipeline {

    agent {
        docker { image 'maven:3.8.2-jdk-11'}
    }

    stages {
        stage ('Stage 1')  {
            steps {
                sh 'ls -lrt'
            }
        }
    }
}