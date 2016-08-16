package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yongliuli on 8/16/16.
 */
public class DashBoard {
    @JsonProperty("_embedded")
    EmbeddedPipelineGroups embedded;

    public EmbeddedPipelineGroups getEmbedded() {
        return embedded;
    }

    public DashBoard setEmbedded(EmbeddedPipelineGroups embedded) {
        this.embedded = embedded;
        return this;
    }
}
