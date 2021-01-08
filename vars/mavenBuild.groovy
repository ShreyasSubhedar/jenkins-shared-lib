def call(Map params=[:], Closure body={}) {
    def compile = params.containsKey('compile') ? params.get('compile') : true
    def test = params.containsKey('test') ? params.get('test') : false
    def packaging = params.containsKey('package') ? params.get('package') : false
    node {
        stage("Compile") {
            if (compile) {
                sh "./mvnw clean compile"
            }
        }

        stage("Unit Test") {
            if (test) {
                sh "./mvnw test"
            }
        }

        stage("Package as a Jar") {
            if (packaging) {
                sh "./mvnw package -DskipTests=true"
            }
        }
        body()
    }
}