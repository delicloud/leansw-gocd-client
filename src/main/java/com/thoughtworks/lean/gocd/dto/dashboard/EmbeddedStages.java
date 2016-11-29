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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddedStages that = (EmbeddedStages) o;

        return stages != null ? stages.equals(that.stages) : that.stages == null;

    }

    @Override
    public int hashCode() {
        return stages != null ? stages.hashCode() : 0;
    }
}
