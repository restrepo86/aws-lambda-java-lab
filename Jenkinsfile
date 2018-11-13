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
      environment {
        scannerHome = 'SonarScanner3'
      }
      steps {
        withSonarQubeEnv ('SonarQube Cloud'){
          sh "wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.2.0.1227-linux.zip && unzip sonar-scanner-cli-3.2.0.1227-linux.zip"
          sh "sonar-scanner-3.2.0.1227-linux/bin/sonar-scanner -Dproject.settings=sonar.properties"
        }
      }
    }
  }
}
