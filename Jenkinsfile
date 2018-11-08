pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''


./gradlew build -x test'''
        sh 'ls -lrt'
      }
    }
    stage('Test') {
      steps{
        echo "Hola"
        sh './gradlew test'
      }
    }
  }
}