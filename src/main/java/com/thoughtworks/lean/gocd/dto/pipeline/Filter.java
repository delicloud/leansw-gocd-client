package com.thoughtworks.lean.gocd.dto.pipeline;

public class Filter {
    private String[] ignore;

    public String[] getIgnore() {
        return ignore;
    }

    public Filter setIgnore(String[] ignore) {
        this.ignore = ignore;
        return this;
    }
}
