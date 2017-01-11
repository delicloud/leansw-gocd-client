package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class DependencyMaterialAttributes implements MaterialAttributes {

    private String name;

    private String pipeline;

    private String stage;

    @JsonProperty("auto_update")
    private Boolean autoUpdate = true;

    public DependencyMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public DependencyMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getPipeline() {
        return pipeline;
    }

    public DependencyMaterialAttributes setPipeline(String pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public String getStage() {
        return stage;
    }

    public DependencyMaterialAttributes setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public DependencyMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("pipeline", pipeline)
                .append("stage", stage)
                .append("autoUpdate", autoUpdate)
                .toString();
    }
}
