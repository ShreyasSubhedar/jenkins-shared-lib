def call(Map params = [:]) {
    def compile = params.containsKey('compile') ? params.get('compile') : true
    def test = params.containsKey('test') ? params.get('test') : false
    def packaging = params.containsKey('package') ? params.get('package') : false
    def dependencyTree = params.containsKey('dependencyTree') ? params.get('dependencyTree') : false

    if (compile) {
        sh(script: "./mvnw clean compile")
    }

    if (test) {
        sh(script: "./mvnw test")
    }

    if (packaging) {
        sh(script: "./mvnw package -DskipTests=true")
    }

    if (dependencyTree) {
        sh(script: "./mvnw dependency:tree")
    }

}