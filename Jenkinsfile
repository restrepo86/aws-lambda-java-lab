pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''
        ./gradlew build -x test
        '''
        sh 'ls -lrt'
      }
    }
    stage('Test') {
      steps{
        sh './gradlew test'
      }
      post {
            always {
              archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
              junit "build/test-results/test/*.xml"
            }
        }
    }
  }
}
