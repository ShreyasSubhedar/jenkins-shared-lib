def call(Map config = [:], Closure body) {
    pipeline {
        agent any
        stages {
            git url: "https://github.com/werne2j/sample-nodejs"
            stage("Install") {
                sh "npm install"
            }
            stage("Test") {
                sh "npm test"
            }
            stage("Deploy") {
                if (config.deploy) {
                    sh "npm publish"
                }
            }
            body()
        }
    }
}