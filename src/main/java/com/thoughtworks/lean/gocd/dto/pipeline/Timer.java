package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Timer {

    private String spec;
    @JsonProperty("only_on_changes")
    private Boolean onlyOnChanges;

    public String getSpec() {
        return spec;
    }

    public Timer setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    public Boolean getOnlyOnChanges() {
        return onlyOnChanges;
    }

    public Timer setOnlyOnChanges(Boolean onlyOnChanges) {
        this.onlyOnChanges = onlyOnChanges;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("spec", spec)
                .append("onlyOnChanges", onlyOnChanges)
                .toString();
    }
}
