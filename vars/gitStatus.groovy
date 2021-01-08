def call(Map params = [:]) {
    return sh(label: 'Get git status',
            script: 'git status',
            returnStdout: true)
}
