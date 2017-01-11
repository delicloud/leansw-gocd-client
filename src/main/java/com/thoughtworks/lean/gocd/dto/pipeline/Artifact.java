package com.thoughtworks.lean.gocd.dto.pipeline;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Artifact {

    private String type;

    private String source;

    private String destination;

    public Artifact() {
    }

    public String getType() {
        return type;
    }

    public Artifact setType(String type) {
        this.type = type;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Artifact setSource(String source) {
        this.source = source;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public Artifact setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("source", source)
                .append("destination", destination)
                .toString();
    }
}
