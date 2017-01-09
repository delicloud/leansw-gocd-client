package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.List;

public class Template {
    private String name;
    private List<Stage> stages;

    public Template() {
    }

    public Template(String name, List<Stage> stages) {
        this.name = name;
        this.stages = stages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
