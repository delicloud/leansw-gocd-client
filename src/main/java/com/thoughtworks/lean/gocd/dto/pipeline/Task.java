package com.thoughtworks.lean.gocd.dto.pipeline;

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
