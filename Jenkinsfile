pipeline {
    agent any
  
    stages {
        stage('Checkout') {

             steps {
             
               script {

                  if (env.BRANCH_NAME == "develop") {
                     echo 'Checkout develop...'
                     // send build started notifications
                    slackSend (channel: '#notificacoes-cdt', color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                     git([url: 'https://topor-builders@bitbucket.org/platformbuilders/treinamento.git', branch: 'develop', credentialsId: '30bb328c-34ee-42c6-9d70-6e5b4be3fc2f'])
                  } else if (env.BRANCH_NAME == "master") {
                     echo 'Checkout master...'
                    // send build started notifications
                     slackSend (channel: '#notificacoes-cdt', color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                     git([url: 'https://topor-builders@bitbucket.org/platformbuilders/treinamento.git', branch: 'master', credentialsId: '30bb328c-34ee-42c6-9d70-6e5b4be3fc2f'])
                  } else {
                     echo 'Checkout Outra Branch...'
                    // send build started notifications
                     slackSend (channel: '#notificacoes-cdt', color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                     git([url: 'https://topor-builders@bitbucket.org/platformbuilders/treinamento.git', branch: env.BRANCH_NAME, credentialsId: '30bb328c-34ee-42c6-9d70-6e5b4be3fc2f'])
                  } 
               }
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building..'
                script {
                    def mvnHome = tool 'M3'
                    sh "${mvnHome}/bin/mvn clean install"
                }
                stash includes: '**', name: 'app' 
            }
        }
        
        stage('Unit Test') {
            steps {
                echo 'Unit Tests..'
                script {
                    def mvnHome = tool 'M3'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }
        
       
        stage('Docker Build') {
            steps {
               echo 'Building Image and Publishing....'
               unstash 'app'
      
               script {
                if (env.BRANCH_NAME == "develop") {
               
                  def app = docker.build("builders-treinamento/treinamento-develop:latest")
                  docker.withRegistry('https://gcr.io', 'gcr:Builders-PagaContas') {
                     app.push("${env.BUILD_NUMBER}")
                     app.push("latest")
                  }

                } else if (env.BRANCH_NAME == "master") {
                  def app = docker.build("builders-treinamento/treinamento-homolog:latest")
                  docker.withRegistry('https://gcr.io', 'gcr:Builders-PagaContas') {
                     app.push("${env.BUILD_NUMBER}")
                     app.push("latest")
                  }
                }
              }
              stash includes: '**', name: 'app' 
            }
        }
            
        stage('Deploy') {

            steps {
              unstash 'app'
              script {
              
                if (env.BRANCH_NAME == "develop") {
                   echo 'Deletando Deploying....Develop'
                   try {
                      sh 'kubectl delete deployment treinamento-deployment --namespace=develop'
                      sh 'kubectl apply -f treinamento-deployment-develop.yaml'
                   } catch (err) {
                      sh 'kubectl apply -f treinamento-deployment-develop.yaml'
                   }

                } else if (env.BRANCH_NAME == "master") {
                   echo 'Deletando Deploying....Homolog'
                   
                   try {
                      sh 'kubectl delete deployment treinamento-homolog --namespace=homolog'
                      sh 'kubectl apply -f treinamento-deployment-homolog.yaml'
                   } catch (err) {
                      sh 'kubectl apply -f treinamento-deployment-homolog.yaml'
                   }
                }
              }  
           }
        }    
    }
    
    post {
      success {
          archive "target/**/*"
          junit 'target/surefire-reports/*.xml'
          slackSend (channel: '#notificacoes-cdt',color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
      }

     failure {
          slackSend (channel: '#notificacoes-cdt', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
     }
    }
}