package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class NantTaskAttributes implements TaskAttributes{

    @JsonProperty("run_if")
    private List<String> runIf;

    @JsonProperty("build_file")
    private String buildFile;

    private String target;

    @JsonProperty("nant_path")
    private String nantPath;

    @JsonProperty("working_directory")
    private String workingDirectory;

    @JsonProperty("on_cancel")
    private Task onCancel;

    public NantTaskAttributes() {
    }

    public List<String> getRunIf() {
        return runIf;
    }

    public NantTaskAttributes setRunIf(List<String> runIf) {
        this.runIf = runIf;
        return this;
    }

    public String getBuildFile() {
        return buildFile;
    }

    public NantTaskAttributes setBuildFile(String buildFile) {
        this.buildFile = buildFile;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public NantTaskAttributes setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getNantPath() {
        return nantPath;
    }

    public NantTaskAttributes setNantPath(String nantPath) {
        this.nantPath = nantPath;
        return this;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public NantTaskAttributes setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }

    public Task getOnCancel() {
        return onCancel;
    }

    public NantTaskAttributes setOnCancel(Task onCancel) {
        this.onCancel = onCancel;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("runIf", runIf)
                .append("buildFile", buildFile)
                .append("target", target)
                .append("nantPath", nantPath)
                .append("workingDirectory", workingDirectory)
                .append("onCancel", onCancel)
                .toString();
    }
}
