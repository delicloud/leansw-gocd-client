package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private long id;

    private boolean fetch_materials=true;

    private String name;

    private String pipelineName;

    private List<Job> jobs = new ArrayList<>();


    public boolean isFetch_materials() {
        return fetch_materials;
    }

    public void setFetch_materials(boolean fetch_materials) {
        this.fetch_materials = fetch_materials;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Stage setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }
}
