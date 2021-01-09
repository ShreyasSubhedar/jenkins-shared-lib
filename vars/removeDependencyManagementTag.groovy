/**
 Remove <dependencyManagement>, <dependencyManagement> tag from the pom.xml

 removeDependencyManagementTag()
 */

def call(Map params = [:]){
    def basedir =  params.containsKey('basedir') ? params.basedir : "src"
    def pomFileName = "${basedir}/pom.xml"

    log(level: 'WARN', text: "Remove <dependencyManagement> tag from the ${basedir}/pom.xml to allow to control if the GAVs exist")
    def pomXml = readFile(file: "${pomFileName}")
    def pomModified = pomXml.replace('<dependencyManagement>', '').replace('</dependencyManagement>', '')
    writeFile(file: "${pomFileName}", text: pomModified)
}