def call(Map args=[:], Closure body={}) {
    node {
        //git url: "${args.repo}", branch: "${args.branch}"
        checkout scm: [$class: 'GitSCM',
                       branches: [[name: 'refs/tags/' + "${args.branch}"]],
                       userRemoteConfigs: [[url: "${args.repo}"]]]

        stage("Compile") {
            sh "./mvnw clean compile"
        }

        stage("Unit Test") {
            sh "./mvnw test"
        }

        stage("Integration Test") {
            sh "./mvnw verify"
        }

        stage("Package Artifact Jar") {
            if (args.package) {
                sh "./mvnw package -DskipTests=true"
            }
        }
        body()
    }
}