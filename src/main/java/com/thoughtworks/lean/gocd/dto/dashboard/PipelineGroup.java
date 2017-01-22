package com.thoughtworks.lean.gocd.dto.dashboard;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

public class PipelineGroup {
    private String name;
    private Collection<Pipeline> pipelines;

    public PipelineGroup() {
    }

    @JsonProperty("_embedded")
    private void unpackObjectsFromEmbededObject(Map<String, Collection<Pipeline>> embeded) {
        pipelines = embeded.get("pipelines");
    }

    public String getName() {
        return name;
    }

    public PipelineGroup setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<Pipeline> getPipelines() {
        return pipelines;
    }

    public PipelineGroup setPipelines(Collection<Pipeline> pipelines) {
        this.pipelines = pipelines;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PipelineGroup that = (PipelineGroup) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return pipelines != null ? pipelines.equals(that.pipelines) : that.pipelines == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pipelines != null ? pipelines.hashCode() : 0);
        return result;
    }
}
