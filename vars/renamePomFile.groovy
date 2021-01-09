/**
 Rename the pom file to backup it

 renamePomFile(ext: .bk)
 */

def call(Map params = [:]){
    def ext = params.containsKey('ext') ? params.ext : ".bk"
    def basedir =  params.containsKey('basedir') ? params.basedir : "src"

    def pomFileName = "${basedir}/pom.xml"
    def pomBackupFileName = "${basedir}/${pomFileName}${ext}"

    log(level: 'WARN', text: "Backup pom file to ${pomBackupFileName}")
    def pomXml = readFile(file: "${pomFileName}")
    writeFile(file: "${pomBackupFileName}", text: pomXml)
    log(level: 'WARN', text: "${basedir}/pom.xml file backuped")
}