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
          archiveArtifacts(artifacts: "build/distributions/${packageName}", fingerprint: true)
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
    stage('Create Bucket/Update file') {
      steps {
        withAWS(credentials: 'awslab', region: 'us-east-1') {
          cfnUpdate(stack: "${projectName}-s3", create: true, file: 's3.yaml', params:["BucketNameLambda=${projectName}-bucket"])
          s3Upload(bucket: "${projectName}-bucket", file: "${packageName}", workingDir: 'build/distributions/')
        }
      }
    }
    stage('Deploy Lambda') {
      steps {
        withAWS(credentials: 'awslab', region: 'us-east-1') {
          cfnUpdate(stack: "${projectName}-lambda", create: true, file: 'lambda.yaml',
            params:[
              "ProjectName=${projectName}",
              "PackageName=${packageName}",
              "BucketName=${projectName}-bucket"
            ])
          cfnDescribe "${projectName}-lambda"
        }
      }
    }
  }
  environment {
    projectName = 'juanes-project'
    packageName = "lambdaGradle-${BUILD_NUMBER}.zip"
  }
}
