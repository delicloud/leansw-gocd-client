package com.thoughtworks.lean.gocd.dto.pipeline;

public class Material {

    private long id;
    private String pipelineName;
    private String type;
    private String description;
    private Attributes attributes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Material setAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }
}
