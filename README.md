## Jenkins Shared Lib

This project contains the example described under the following [blog post](https://itnext.io/how-to-build-your-own-jenkins-shared-library-9dc129db260c)
and has been extended to include some `JenkinsPipelineSpecification` groovy files which allows to mock jenkins pipelines.

## How to play

- Create the `Global Pipeline librairies using this url: `http://localhost:8080/configure`. Define the name of the shared lib, check the box `Load implicitly`
  and pass the Git URL of the project to be clones
- Create next a `Pipeline NodeJS DSL job` using the following syntax:
```
node {
    env.NODEJS_HOME = "${tool 'node'}"
    env.PATH = "${env.NODEJS_HOME}/bin:${env.PATH}"
    
    buildJavascriptApp deploy: false, {
        notify type: "slack", message: "Build succeeded"
    }
}
```

or `Pipeline DSL` using the `buildJavaApp.groovy` script

```
node { 
    buildJavaApp(repo: https://github.com/snowdrop/rest-http-example.git)
}
```