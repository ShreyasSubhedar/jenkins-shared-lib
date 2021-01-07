## Jenkins Shared Lib

- Setup the shared lib
- Create a pipeline DSL job
```
node {
    env.NODEJS_HOME = "${tool 'node'}"
    env.PATH = "${env.NODEJS_HOME}/bin:${env.PATH}"
    
    buildJavascriptApp deploy: false, {
        notify type: "slack", message: "Build succeeded"
    }
}
```