/**
 Rename the pom file to backup it

 renamePomFile(ext: .bk)
 */

def call(Map params = [:]){
    def ext = params.containsKey('ext') ? params.ext : ".bk"
    return ext
}