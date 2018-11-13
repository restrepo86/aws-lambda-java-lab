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
               junit "**/TEST-com.lambda.gradle.*.xml"
            }
        }
    }
  }
}
