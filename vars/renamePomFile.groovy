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
}