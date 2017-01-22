package com.thoughtworks.lean.gocd.dto.dashboard;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

public class DashBoard {


    private Collection<PipelineGroup> pipelineGroups;

    public DashBoard() {
    }

    @JsonProperty("_embedded")
    private void unpackObjectsFromEmbededObject(Map<String, Collection<PipelineGroup>> embeded) {
        pipelineGroups = embeded.get("pipeline_groups");
    }

    public Collection<PipelineGroup> getPipelineGroups() {
        return pipelineGroups;
    }

    public DashBoard setPipelineGroups(Collection<PipelineGroup> pipelineGroups) {
        this.pipelineGroups = pipelineGroups;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashBoard dashBoard = (DashBoard) o;

        return pipelineGroups != null ? pipelineGroups.equals(dashBoard.pipelineGroups) : dashBoard.pipelineGroups == null;

    }

    @Override
    public int hashCode() {
        return pipelineGroups != null ? pipelineGroups.hashCode() : 0;
    }
}
