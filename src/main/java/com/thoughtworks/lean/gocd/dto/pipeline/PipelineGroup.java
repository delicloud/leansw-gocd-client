package com.thoughtworks.lean.gocd.dto.pipeline;

public class PipelineGroup {
    String name;

    public PipelineGroup() {
    }

    public String getName() {
        return name;
    }

    public PipelineGroup setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "PipelineGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
