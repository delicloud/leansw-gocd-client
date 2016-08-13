package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private String name = "defaultJob";
    private String run_instance_count;
    private long timeout;
    private List<EnvironmentVariables> environment_variables = new ArrayList<>();
    private List<String> resources = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRun_instance_count() {
        return run_instance_count;
    }

    public void setRun_instance_count(String run_instance_count) {
        this.run_instance_count = run_instance_count;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public List<EnvironmentVariables> getEnvironment_variables() {
        return environment_variables;
    }

    public void setEnvironment_variables(List<EnvironmentVariables> environment_variables) {
        this.environment_variables = environment_variables;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", run_instance_count='" + run_instance_count + '\'' +
                ", timeout=" + timeout +
                ", environment_variables=" + environment_variables +
                ", resources=" + resources +
                ", tasks=" + tasks +
                '}';
    }
}
