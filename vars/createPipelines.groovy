import hudson.model.FreeStyleProject
import hudson.model.Job;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.BranchSpec;
import hudson.triggers.SCMTrigger
import javaposse.jobdsl.plugin.*;
import jenkins.model.Jenkins;

def call(Map params = [:]) {
    def jenkins = Jenkins.getInstanceOrNull();

    def seedJobsUrl = "https://gitlab.cee.redhat.com/snowdrop/jenkins-jobs-dsl.git"
    def jobName = "seed-jobs";
    def branch = "*/master"

    jenkins.items.findAll { job -> job.name == jobName }
            .each { job -> job.delete() }

    gitTrigger = new SCMTrigger("H * * * *");
    dslBuilder = new ExecuteDslScripts()

    dslBuilder.setTargets("jobs/**/*Job.groovy")
    dslBuilder.setUseScriptText(false)
    dslBuilder.setIgnoreExisting(false)
    dslBuilder.setIgnoreMissingFiles(false)
    dslBuilder.setRemovedJobAction(RemovedJobAction.DELETE)
    dslBuilder.setRemovedViewAction(RemovedViewAction.DELETE)
    dslBuilder.setLookupStrategy(LookupStrategy.SEED_JOB)

    dslProject = new hudson.model.FreeStyleProject(jenkins, jobName);
    dslProject.scm = new GitSCM(seedJobsUrl);
    dslProject.scm.branches = [new BranchSpec(branch)];
    dslProject.addTrigger(gitTrigger);
    dslProject.createTransientActions();
    dslProject.getBuildersList().add(dslBuilder);

    jenkins.add(dslProject, jobName);

    // Start to trigger the SCM project
    gitTrigger.start(dslProject, true);

    // Find the job to be scheduled
    def allJobs = jenkins.getItems(Job.class);
    seedJob = allJobs.find {job -> job.name == jobName};

    // Queue the job to launch it
    log("INFO", text: "Scheduling job name is: ${seedJob}")
    jenkins.queue.schedule(seedJob)


}
