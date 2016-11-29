package com.thoughtworks.lean.gocd.dto.dashboard;

import java.util.List;

/**
 * Created by yongliuli on 8/16/16.
 */
public class EmbeddedInstances {
    List<PipelineInstance> instances;

    public List<PipelineInstance> getInstances() {
        return instances;
    }

    public EmbeddedInstances setInstances(List<PipelineInstance> instances) {
        this.instances = instances;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddedInstances that = (EmbeddedInstances) o;

        return instances != null ? instances.equals(that.instances) : that.instances == null;

    }

    @Override
    public int hashCode() {
        return instances != null ? instances.hashCode() : 0;
    }
}
