package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Material {

    private long id;
    private String pipelineName;
    private String type;
    private String description;
    private Attributes attributes;

    public long getId() {
        return id;
    }

    public Material setId(long id) {
        this.id = id;
        return this;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public Material setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }

    public String getType() {
        return type;
    }

    public Material setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Material setDescription(String description) {
        this.description = description;
        return this;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({ @JsonSubTypes.Type(value = GitMaterialAttributes.class, name = "git")})
    public Material setAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public static Material emptyGitRepo() {
        return new Material()
                .setType("git")
                .setAttributes(new GitMaterialAttributes()
                        .setUrl("git@github.com:sample_repo/example.git")
                        .setDestination("dest")
                        .setAuto_update(true)
                        .setBranch("master"));
    }
}
