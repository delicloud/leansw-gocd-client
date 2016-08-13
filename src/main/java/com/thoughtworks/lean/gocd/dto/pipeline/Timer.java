package com.thoughtworks.lean.gocd.dto.pipeline;

public class Timer {

    private String spec;
    private Boolean only_on_changes;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Boolean getOnly_on_changes() {
        return only_on_changes;
    }

    public void setOnly_on_changes(Boolean only_on_changes) {
        this.only_on_changes = only_on_changes;
    }
}
