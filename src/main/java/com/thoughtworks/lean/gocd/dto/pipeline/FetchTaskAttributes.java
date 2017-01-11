package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class FetchTaskAttributes implements TaskAttributes{

    @JsonProperty("run_if")
    private List<String> runIf;

    private String pipeline;

    private String stage;

    private String job;

    private String source;

    @JsonProperty("is_source_a_file")
    private  Boolean isSourceAFile;

    private String destination;

    @JsonProperty("on_cancel")
    private Task onCancel;

    public FetchTaskAttributes() {
    }

    public List<String> getRunIf() {
        return runIf;
    }

    public FetchTaskAttributes setRunIf(List<String> runIf) {
        this.runIf = runIf;
        return this;
    }

    public String getPipeline() {
        return pipeline;
    }

    public FetchTaskAttributes setPipeline(String pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public String getStage() {
        return stage;
    }

    public FetchTaskAttributes setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public String getJob() {
        return job;
    }

    public FetchTaskAttributes setJob(String job) {
        this.job = job;
        return this;
    }

    public String getSource() {
        return source;
    }

    public FetchTaskAttributes setSource(String source) {
        this.source = source;
        return this;
    }

    public Boolean getSourceAFile() {
        return isSourceAFile;
    }

    public FetchTaskAttributes setSourceAFile(Boolean sourceAFile) {
        isSourceAFile = sourceAFile;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public FetchTaskAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Task getOnCancel() {
        return onCancel;
    }

    public FetchTaskAttributes setOnCancel(Task onCancel) {
        this.onCancel = onCancel;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("runIf", runIf)
                .append("pipeline", pipeline)
                .append("stage", stage)
                .append("job", job)
                .append("source", source)
                .append("isSourceAFile", isSourceAFile)
                .append("destination", destination)
                .append("onCancel", onCancel)
                .toString();
    }
}
