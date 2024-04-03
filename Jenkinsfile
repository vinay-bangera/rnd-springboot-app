pipeline {

    agent {
        docker { image 'alpine'}
    }

    stages {
        stage ('Stage 1')  {
            steps {
                sh 'ls -lrt'
            }
        }
    }
}