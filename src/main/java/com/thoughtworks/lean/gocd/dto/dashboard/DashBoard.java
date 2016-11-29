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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashBoard dashBoard = (DashBoard) o;

        return embedded != null ? embedded.equals(dashBoard.embedded) : dashBoard.embedded == null;

    }

    @Override
    public int hashCode() {
        return embedded != null ? embedded.hashCode() : 0;
    }
}
