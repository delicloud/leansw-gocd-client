package com.thoughtworks.lean.gocd.dto.pipeline;


public class PipelineCreate {
    String group;
    PipelineConfig pipeline;

    public PipelineCreate() {
    }

    public PipelineCreate(String group, PipelineConfig pipeline) {
        this.group = group;
        this.pipeline = pipeline;
    }

    public String getGroup() {
        return group;
    }

    public PipelineCreate setGroup(String group) {
        this.group = group;
        return this;
    }

    public PipelineConfig getPipeline() {
        return pipeline;
    }

    public PipelineCreate setPipeline(PipelineConfig pipeline) {
        this.pipeline = pipeline;
        return this;
    }
}
