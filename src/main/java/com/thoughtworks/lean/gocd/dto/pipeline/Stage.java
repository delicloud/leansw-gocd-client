package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private long id;

    private Boolean fetch_materials = true;

    private String name;

    private String pipelineName;

    private Boolean clean_working_directory = false;

    private Boolean never_cleanup_artifacts = false;

    private List<EnvironmentVariables> environment_variables;

    private Approval approval;

    private List<Job> jobs = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Stage setId(long id) {
        this.id = id;
        return this;
    }

    public Stage setFetch_materials(Boolean fetch_materials) {
        this.fetch_materials = fetch_materials;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stage setName(String name) {
        this.name = name;
        return this;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public Stage setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Stage setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Boolean getFetch_materials() {
        return fetch_materials;
    }

    public Boolean getClean_working_directory() {
        return clean_working_directory;
    }

    public Stage setClean_working_directory(Boolean clean_working_directory) {
        this.clean_working_directory = clean_working_directory;
        return this;
    }

    public Boolean getNever_cleanup_artifacts() {
        return never_cleanup_artifacts;
    }

    public Stage setNever_cleanup_artifacts(Boolean never_cleanup_artifacts) {
        this.never_cleanup_artifacts = never_cleanup_artifacts;
        return this;
    }

    public List<EnvironmentVariables> getEnvironment_variables() {
        return environment_variables;
    }

    public Stage setEnvironment_variables(List<EnvironmentVariables> environment_variables) {
        this.environment_variables = environment_variables;
        return this;
    }

    public Approval getApproval() {
        return approval;
    }

    public Stage setApproval(Approval approval) {
        this.approval = approval;
        return this;
    }
}

