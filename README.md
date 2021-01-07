## Jenkins Shared Lib

Using example: https://itnext.io/how-to-build-your-own-jenkins-shared-library-9dc129db260c

- Create the `Global Pipeline librairies using this url: `http://localhost:8080/configure`. Define the name of the shared lib, check the box `Load implicitly`
  and pass the Git URL of the project to be clones
- Create next a `Pipeline DSL job` using the following syntax:
```
node {
    env.NODEJS_HOME = "${tool 'node'}"
    env.PATH = "${env.NODEJS_HOME}/bin:${env.PATH}"
    
    buildJavascriptApp deploy: false, {
        notify type: "slack", message: "Build succeeded"
    }
}
```