def call(Map params = [:]) {
    return sh(label: 'Check git status of the repo',
            script: 'git status',
            returnStdout: true)
}
