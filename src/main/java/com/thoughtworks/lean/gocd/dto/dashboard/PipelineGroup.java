package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yongliuli on 8/16/16.
 */
public class PipelineGroup {
    private String name;
    @JsonProperty("_embedded")
    private EmbeddedPipelines embedded;

    public String getName() {
        return name;
    }

    public PipelineGroup setName(String name) {
        this.name = name;
        return this;
    }

    public EmbeddedPipelines getEmbedded() {
        return embedded;
    }

    public PipelineGroup setEmbedded(EmbeddedPipelines embedded) {
        this.embedded = embedded;
        return this;
    }
}
