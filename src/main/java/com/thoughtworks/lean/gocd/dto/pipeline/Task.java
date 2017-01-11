package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Task {

    private String type;
    private TaskAttributes attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TaskAttributes getAttributes() {
        return attributes;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = ExecTaskAttributes.class, name = "exec"),
            @JsonSubTypes.Type(value = PluggableTaskAttributes.class, name = "pluggable_task"),
            @JsonSubTypes.Type(value = AntTaskAttributes.class, name = "ant"),
            @JsonSubTypes.Type(value = NantTaskAttributes.class, name = "nant"),
            @JsonSubTypes.Type(value = RakeTaskAttributes.class, name = "rake"),
            @JsonSubTypes.Type(value = FetchTaskAttributes.class, name = "fetch")
    })
    public void setAttributes(TaskAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Task{" +
                "type='" + type + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
