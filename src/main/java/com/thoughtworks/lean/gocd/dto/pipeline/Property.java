package com.thoughtworks.lean.gocd.dto.pipeline;

public class Property {

    private String name;

    private String source;

    private String xpath;

    public Property() {
    }

    public String getName() {
        return name;
    }

    public Property setName(String name) {
        this.name = name;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Property setSource(String source) {
        this.source = source;
        return this;
    }

    public String getXpath() {
        return xpath;
    }

    public Property setXpath(String xpath) {
        this.xpath = xpath;
        return this;
    }
}
