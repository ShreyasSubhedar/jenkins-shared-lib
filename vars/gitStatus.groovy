def call(Map params = [:]) {
    def basedir =  params.containsKey('basedir') ? params.basedir : "src"
    return sh(label: 'Check git status of the repo',
            script: "cd ${basedir} && git status",
            returnStdout: true)
}
