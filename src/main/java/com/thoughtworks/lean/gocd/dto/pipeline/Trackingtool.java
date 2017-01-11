package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Trackingtool {
    private String type;
    private TrackingtoolAttributes attributes;

    public Trackingtool() {
    }

    public String getType() {
        return type;
    }

    public Trackingtool setType(String type) {
        this.type = type;
        return this;
    }

    public TrackingtoolAttributes getAttributes() {
        return attributes;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({ @JsonSubTypes.Type(value = GenericTrackingtoolAttributes.class, name = "generic"),
            @JsonSubTypes.Type(value = MingleTrackingtoolAttributes.class, name = "mingle") })
    public Trackingtool setAttributes(TrackingtoolAttributes attributes) {
        this.attributes = attributes;
        return this;
    }
}
