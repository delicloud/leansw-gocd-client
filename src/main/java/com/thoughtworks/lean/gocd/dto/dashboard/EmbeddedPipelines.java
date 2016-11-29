package com.thoughtworks.lean.gocd.dto.dashboard;

import java.util.List;

/**
 * Created by yongliuli on 8/16/16.
 */
public class EmbeddedPipelines {
    private List<Pipeline> pipelines;

    public List<Pipeline> getPipelines() {
        return pipelines;
    }

    public EmbeddedPipelines setPipelines(List<Pipeline> pipelines) {
        this.pipelines = pipelines;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddedPipelines that = (EmbeddedPipelines) o;

        return pipelines != null ? pipelines.equals(that.pipelines) : that.pipelines == null;

    }

    @Override
    public int hashCode() {
        return pipelines != null ? pipelines.hashCode() : 0;
    }
}
