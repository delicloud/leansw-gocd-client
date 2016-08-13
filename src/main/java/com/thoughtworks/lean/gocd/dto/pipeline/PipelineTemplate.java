package com.thoughtworks.lean.gocd.dto.pipeline;

public class PipelineTemplate {

    private String group;

    private PipelineConfig pipeline;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public PipelineConfig getPipeline() {
        return pipeline;
    }

    public void setPipeline(PipelineConfig pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public String toString() {
        return "PipelineTemplate{" +
                "group='" + group + '\'' +
                ", pipeline=" + pipeline +
                '}';
    }
}
