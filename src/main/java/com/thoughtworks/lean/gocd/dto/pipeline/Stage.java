package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Stage {

    private long id;

    private String name;

    @JsonProperty("fetch_materials")
    private Boolean fetchMaterials = true;

    @JsonProperty("clean_working_directory")
    private Boolean cleanWorkingDirectory = false;

    @JsonProperty("never_cleanup_artifacts")
    private Boolean neverCleanupArtifacts = false;

    private Approval approval;

    @JsonProperty("environment_variables")
    private List<EnvironmentVariable> environmentVariables;

    private List<Job> jobs;

    private String pipelineName;

    public Stage() {
    }

    public long getId() {
        return id;
    }

    public Stage setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stage setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getFetchMaterials() {
        return fetchMaterials;
    }

    public Stage setFetchMaterials(Boolean fetchMaterials) {
        this.fetchMaterials = fetchMaterials;
        return this;
    }

    public Boolean getCleanWorkingDirectory() {
        return cleanWorkingDirectory;
    }

    public Stage setCleanWorkingDirectory(Boolean cleanWorkingDirectory) {
        this.cleanWorkingDirectory = cleanWorkingDirectory;
        return this;
    }

    public Boolean getNeverCleanupArtifacts() {
        return neverCleanupArtifacts;
    }

    public Stage setNeverCleanupArtifacts(Boolean neverCleanupArtifacts) {
        this.neverCleanupArtifacts = neverCleanupArtifacts;
        return this;
    }

    public Approval getApproval() {
        return approval;
    }

    public Stage setApproval(Approval approval) {
        this.approval = approval;
        return this;
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return environmentVariables;
    }

    public Stage setEnvironmentVariables(List<EnvironmentVariable> environmentVariables) {
        this.environmentVariables = environmentVariables;
        return this;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Stage setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public Stage setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("fetchMaterials", fetchMaterials)
                .append("cleanWorkingDirectory", cleanWorkingDirectory)
                .append("neverCleanupArtifacts", neverCleanupArtifacts)
                .append("approval", approval)
                .append("environmentVariables", environmentVariables)
                .append("jobs", jobs)
                .append("pipelineName", pipelineName)
                .toString();
    }
}

