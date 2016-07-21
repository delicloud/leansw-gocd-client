package com.thoughtworks.lean.gocd;

public class JobParams {
    private String pipeline;
    private int pipelineCounter;
    private String stage;
    private int stageCounter;
    private String job;

    public JobParams() {
    }

    public JobParams(String pipeline, int pipelineCounter, String stage, int stageCounter, String job) {
        this.pipeline = pipeline;
        this.pipelineCounter = pipelineCounter;
        this.stage = stage;
        this.stageCounter = stageCounter;
        this.job = job;
    }

    public String getPipeline() {
        return pipeline;
    }

    public JobParams setPipeline(String pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public int getPipelineCounter() {
        return pipelineCounter;
    }

    public JobParams setPipelineCounter(int pipelineCounter) {
        this.pipelineCounter = pipelineCounter;
        return this;
    }

    public String getStage() {
        return stage;
    }

    public JobParams setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public int getStageCounter() {
        return stageCounter;
    }

    public JobParams setStageCounter(int stageCounter) {
        this.stageCounter = stageCounter;
        return this;
    }

    public String getJob() {
        return job;
    }

    public JobParams setJob(String job) {
        this.job = job;
        return this;
    }

    @Override
    public String toString() {
        return "JobParams{" +
                "pipeline='" + pipeline + '\'' +
                ", pipelineCounter=" + pipelineCounter +
                ", stage='" + stage + '\'' +
                ", stageCounter=" + stageCounter +
                ", job='" + job + '\'' +
                '}';
    }
}
