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
}
