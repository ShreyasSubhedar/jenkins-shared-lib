/**
 Rename the pom file to backup it

 renamePomFile(ext: .bk)
 */

def call(Map params = [:]){
    def ext = params.containsKey('ext') ? params.ext : ".bk"
    def pomFileName = "pom.xml"
    def pomBackupFileName = "${pomFileName}${ext}"

    println "Backup pom file to ${pomBackupFileName}"
    def pomXml = readFile(file: "${pomFileName}")
    writeFile(file: "${pomBackupFileName}", text: pomXml)

    println "Remove <dependencyManagement> tag from the pom.xml to allow to control if the GAVs exist"
    def pomModified = pomXml.replace('<dependencyManagement>', '').replace('</dependencyManagement>', '')
    writeFile(file: "${pomFileName}", text: pomModified)
    println pomModified

    return ext
}