package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Job {

    private String name = "defaultJob";

    @JsonProperty("run_instance_count")
    private String runInstanceCount;

    private Number timeout;

    @JsonProperty("environment_variables")
    private List<EnvironmentVariable> environmentVariables;

    private List<String> resources;

    private List<Task> tasks;

    private List<Tab> tabs;

    private List<Artifact> artifacts;

    private List<Property> properties;

    @JsonProperty("elastic_profile_id")
    private String elasticProfileId;

    public Job() {
    }

    public String getName() {
        return name;
    }

    public Job setName(String name) {
        this.name = name;
        return this;
    }

    public String getRunInstanceCount() {
        return runInstanceCount;
    }

    public Job setRunInstanceCount(String runInstanceCount) {
        this.runInstanceCount = runInstanceCount;
        return this;
    }

    public Number getTimeout() {
        return timeout;
    }

    public Job setTimeout(Number timeout) {
        this.timeout = timeout;
        return this;
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return environmentVariables;
    }

    public Job setEnvironmentVariables(List<EnvironmentVariable> environmentVariables) {
        this.environmentVariables = environmentVariables;
        return this;
    }

    public List<String> getResources() {
        return resources;
    }

    public Job setResources(List<String> resources) {
        this.resources = resources;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Job setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public Job setTabs(List<Tab> tabs) {
        this.tabs = tabs;
        return this;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public Job setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Job setProperties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

    public String getElasticProfileId() {
        return elasticProfileId;
    }

    public Job setElasticProfileId(String elasticProfileId) {
        this.elasticProfileId = elasticProfileId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("runInstanceCount", runInstanceCount)
                .append("timeout", timeout)
                .append("environmentVariables", environmentVariables)
                .append("resources", resources)
                .append("tasks", tasks)
                .append("tabs", tabs)
                .append("artifacts", artifacts)
                .append("properties", properties)
                .append("elasticProfileId", elasticProfileId)
                .toString();
    }
}
