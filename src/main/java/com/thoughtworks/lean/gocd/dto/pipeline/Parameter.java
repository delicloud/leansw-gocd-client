package com.thoughtworks.lean.gocd.dto.pipeline;

public class Parameter {
    private String name;
    private String value;

    public Parameter() {
    }

    public String getName() {
        return name;
    }

    public Parameter setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Parameter setValue(String value) {
        this.value = value;
        return this;
    }
}
