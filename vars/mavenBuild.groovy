def call(Map params = [:]) {
    def compile = params.containsKey('compile') ? params.get('compile') : true
    def test = params.containsKey('test') ? params.get('test') : false
    def packaging = params.containsKey('package') ? params.get('package') : false
    def dependencyTree = params.containsKey('dependencyTree') ? params.get('dependencyTree') : false
    def workdir = params.containsKey('workdir') ? params.workdir : "${env.WORKSPACE}"

    if (compile) {
        sh(script: "mvn clean compile")
    }

    if (test) {
        sh(script: "mvn test")
    }

    if (packaging) {
        sh(script: "mvn package -DskipTests=true")
    }

    if (dependencyTree) {
        sh(script: "mvn dependency:tree")
    }

}