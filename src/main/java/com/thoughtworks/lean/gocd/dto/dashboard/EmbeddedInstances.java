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
}
