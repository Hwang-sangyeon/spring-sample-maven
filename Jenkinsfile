pipeline {
    agent any

    tools {
        // Jenkins 관리 → Tools → Maven Installations 에 등록한 이름과 정확히 일치해야 함
        maven 'mvn'
    }

    environment {
        // ===== Harbor Registry 정보 =====
        REGISTRY    = "syhwang.harbor.com"
        PROJECT     = "spring-sample-maven"
        IMAGE_NAME  = "spring-sample"
        IMAGE_TAG   = "${env.BUILD_NUMBER}"
        FULL_IMAGE  = "${REGISTRY}/${PROJECT}/${IMAGE_NAME}"
    }

    stages {

        stage('Checkout') {
            steps {
                // GitHub에서 소스 체크아웃
                checkout scm
            }
        }

        stage('Build (Maven)') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Image Build') {
            steps {
                sh "docker build -t ${FULL_IMAGE}:${IMAGE_TAG} ."
                sh "docker tag ${FULL_IMAGE}:${IMAGE_TAG} ${FULL_IMAGE}:latest"
            }
        }

        stage('Push to Harbor') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'registry-credential',
                    usernameVariable: 'REG_USER',
                    passwordVariable: 'REG_PASS'
                )]) {
                    sh """
                        echo \$REG_PASS | docker login ${REGISTRY} -u \$REG_USER --password-stdin
                        docker push ${FULL_IMAGE}:${IMAGE_TAG}
                        docker push ${FULL_IMAGE}:latest
                    """
                }
            }
        }

        stage('Cleanup') {
            steps {
                // 빌드 후 로컬 이미지 정리 (디스크 용량 관리)
                sh "docker rmi ${FULL_IMAGE}:${IMAGE_TAG} ${FULL_IMAGE}:latest || true"
            }
        }
    }

    post {
        success {
            echo "빌드 및 Harbor Push 성공: ${FULL_IMAGE}:${IMAGE_TAG}"
        }
        failure {
            echo "빌드 실패 - Console Output 로그 확인 필요"
        }
        always {
            // Harbor 로그인 세션 정리 (보안)
            sh "docker logout ${REGISTRY} || true"
        }
    }
}