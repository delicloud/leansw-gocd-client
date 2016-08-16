package com.thoughtworks.lean.gocd.dto.dashboard;

import java.util.List;

/**
 * Created by yongliuli on 8/16/16.
 */
public class EmbeddedStages {
    List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public EmbeddedStages setStages(List<Stage> stages) {
        this.stages = stages;
        return this;
    }
}
