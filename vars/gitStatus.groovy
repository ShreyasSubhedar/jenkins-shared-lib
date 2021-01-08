def call(Map params = [:]) {
    return sh(label: 'Check git status of the repo',
            script: 'ls -la && git status',
            returnStdout: true)
}
