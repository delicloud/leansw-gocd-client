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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PipelineGroup that = (PipelineGroup) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return embedded != null ? embedded.equals(that.embedded) : that.embedded == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (embedded != null ? embedded.hashCode() : 0);
        return result;
    }


}
