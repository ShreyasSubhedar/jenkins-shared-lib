/**
 Rename the pom file to backup it

 renamePomFile(ext: .bk)
 */

def call(Map params = [:]){
    def ext = params.containsKey('ext') ? params.ext : ".bk"
    def remove = params.containsKey('removeBackup') ? params.removeBackup : true

    def pomFileName = "pom.xml"
    def pomBackupFileName = "${pomFileName}${ext}"

    println "Restore pom file to ${pomBackupFileName}"
    def pomXml = readFile(file: "${pomBackupFileName}")
    writeFile(file: "${pomFileName}", text: pomXml)
    log(level: 'WARN', text: "pom.xml file restored")

    if (remove) {
        boolean fileSuccessfullyDeleted =  new File(${pomBackupFileName}).delete()
        log(level: 'WARN', text: "File successfuklly deleted: ${fileSuccessfullyDeleted}")
    }
}