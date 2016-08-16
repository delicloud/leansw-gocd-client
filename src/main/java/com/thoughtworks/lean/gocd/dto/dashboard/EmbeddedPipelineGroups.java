package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by yongliuli on 8/16/16.
 */
public class EmbeddedPipelineGroups {
    @JsonProperty("pipeline_groups")
    List<PipelineGroup> pipelineGroups;

    public List<PipelineGroup> getPipelineGroups() {
        return pipelineGroups;
    }

    public EmbeddedPipelineGroups setPipelineGroups(List<PipelineGroup> pipelineGroups) {
        this.pipelineGroups = pipelineGroups;
        return this;
    }
}

