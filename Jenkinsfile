pipeline {
  agent any
  environment {
    projectName = 'juanesProject'
  }
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
      post {
        always {
          archiveArtifacts(artifacts: 'build/libs/**/*.jar', fingerprint: true)
          junit 'build/test-results/test/*.xml'

        }

      }
      steps {
        sh './gradlew test'
      }
    }
    stage('SonarQube') {
      steps {
        withSonarQubeEnv('SonarQube Cloud') {
          sh 'sonar-scanner -Dproject.settings=sonar.properties'
        }

      }
    }
    stage('Create Bucke/Update file'){
      steps {
        withAWS(credentials:'awslab') {
          cfnUpdate(stack: '${projectName}-s3', create: true, file: 's3.yaml')
        }
      }
    }
    stage('Deploy Lambda') {
      steps {
        withAWS(credentials:'awslab') {
          cfnUpdate(stack: '${projectName}-lambda', create: true, file: 'lambda.yaml')
        }
      }
    }
  }
}
