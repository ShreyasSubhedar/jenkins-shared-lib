def call(Map params = [:]) {
    def compile = params.containsKey('compile') ? params.get('compile') : true
    def test = params.containsKey('test') ? params.get('test') : false
    def packaging = params.containsKey('package') ? params.get('package') : false
    def dependencyTree = params.containsKey('dependencyTree') ? params.get('dependencyTree') : false
    def basedir =  params.containsKey('basedir') ? params.basedir : "src"
    def result

    if (compile) {
        log(level: 'WARN', text: "mavenBuild: Execute compile goal")
        sh(script: "mvn -f ${basedir}/pom.xml clean compile")
    }

    if (test) {
        log(level: 'WARN', text: "mavenBuild: Execute test goal")
        sh(script: "mvn -f ${basedir}/pom.xml test")
    }

    if (packaging) {
        log(level: 'WARN', text: "mavenBuild: Execute package goal")
        sh(script: "mvn -f ${basedir}/pom.xml package -DskipTests=true")
    }

    if (dependencyTree) {
        log(level: 'WARN', text: "mavenBuild: Execute dependency:tree")
        result = sh(script: "mvn -f ${basedir}/pom.xml -B dependency:tree", returnStdout: true)
        writeFile(file: "report.txt", text: result)
    }

}