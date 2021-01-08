## Jenkins Shared Lib

This project contains the example described under the following [blog post](https://itnext.io/how-to-build-your-own-jenkins-shared-library-9dc129db260c)
and has been extended to include some `JenkinsPipelineSpecification` groovy files which allows to mock jenkins pipelines.

## How to play

- Create the `Global Pipeline librairies using this url: `http://localhost:8080/configure`. Define the name of the shared lib, check the box `Load implicitly`
  and pass the Git URL of the project to be clones
- Create next a `Pipeline DSL job` using the following syntax:
```
@Library('mytools') _

environment {
    PIPELINE_LOG_LEVEL='INFO'
}

node {
    ansiColor('xterm') {
      echo "TERM=${env.TERM}"
    }
    //sh "printenv"
    gitCheckout(
      repo: "https://github.com/snowdrop/spring-boot-bom.git",
      branch: "2.3.6.Alpha2")
    
    renamePomFile(ext: '.bk')
    removeDependencyManagementTag()
    
    mavenBuild(dependencyTree: true, compile: false)
    restorePomFile(ext: .bk, remove: true)
    
    def status = gitStatus()
    if (status.contains("nothing to commit, working tree clean")) {
      println("No Git difference :-)")     
    } else {  
      log(level: 'ERROR', text: "Git difference observed ${status}. Consult the report.txt file under the workspace")
      sh "exit 1"
    }
}
```