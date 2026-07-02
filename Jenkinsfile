pipeline {
    agent any

    environment {
        KATALON_HOME = "C:\\Katalon\\Katalon_Studio_Engine"
        PROJECT_FILE = "BDD Automation API.prj"
        PROFILE = "default"
        REPORT_DIR = "Reports"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Baludevan09/BDD-Automation-API.git'
            }
        }

        stage('Clean Old Reports') {
            steps {
                bat 'if exist allure-results rmdir /s /q allure-results'
                bat 'if exist allure-report rmdir /s /q allure-report'
            }
        }

        stage('Run API Tests') {
            steps {
                bat """
                %KATALON_HOME%\\katalonc.exe ^
                -noSplash -runMode=console ^
                -projectPath="%WORKSPACE%\\%PROJECT_FILE%" ^
                -testSuitePath="Test Suites/Parallel Run" ^
                -executionProfile="%PROFILE%" ^
                -browserType="Web Service" ^
                -reportFolder="%REPORT_DIR%\\API"
                """
            }
        }

        stage('Run UI Tests') {
            steps {
                bat """
                %KATALON_HOME%\\katalonc.exe ^
                -noSplash -runMode=console ^
                -projectPath="%WORKSPACE%\\%PROJECT_FILE%" ^
                -testSuitePath="Test Suites/UI_Suite" ^
                -executionProfile="%PROFILE%" ^
                -browserType="Chrome" ^
                -reportFolder="%REPORT_DIR%\\UI"
                """
            }
        }

        stage('Run DB Tests') {
            steps {
                bat """
                %KATALON_HOME%\\katalonc.exe ^
                -noSplash -runMode=console ^
                -projectPath="%WORKSPACE%\\%PROJECT_FILE%" ^
                -testSuitePath="Test Suites/DB_Suite" ^
                -executionProfile="%PROFILE%" ^
                -browserType="Web Service" ^
                -reportFolder="%REPORT_DIR%\\DB"
                """
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'allure generate allure-results --clean -o allure-report'
            }
        }
    }

    post {

        always {
            archiveArtifacts artifacts: 'Reports/**/*', fingerprint: true
            archiveArtifacts artifacts: 'allure-report/**/*', fingerprint: true
        }

        success {
            echo '✅ All suites executed successfully'
        }

        failure {
            echo '❌ Test execution failed'
        }
    }
}