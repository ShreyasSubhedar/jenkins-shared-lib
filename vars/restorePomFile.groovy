/**
 Rename the pom file to backup it

 renamePomFile(ext: .bk)
 */

def call(Map params = [:]){
    def ext = params.containsKey('ext') ? params.ext : ".bk"
    def basedir =  params.containsKey('basedir') ? params.basedir : "src"
    def remove = params.containsKey('removeBackup') ? params.removeBackup : true

    def pomFileName = "${basedir}/pom.xml"
    def pomBackupFileName = "${basedir}/${pomFileName}${ext}"

    println "Restore pom file to ${pomBackupFileName}"
    def pomXml = readFile(file: "${pomBackupFileName}")
    writeFile(file: "${pomFileName}", text: pomXml)
    log(level: 'WARN', text: "pom.xml file restored")

    if (remove) {
        // TODO: Check why file is not deleted using --> new File(pomBackupFileName).delete()
        sh(script: "rm ${pomBackupFileName}")
        log(level: 'WARN', text: "'${pomBackupFileName}' file deleted")
    }
}