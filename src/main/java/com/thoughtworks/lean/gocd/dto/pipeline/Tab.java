package com.thoughtworks.lean.gocd.dto.pipeline;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tab {

    private String name;
    private String path;

    public Tab() {
    }

    public String getName() {
        return name;
    }

    public Tab setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Tab setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("path", path)
                .toString();
    }
}
