package com.thoughtworks.lean.gocd.dto.pipeline;

public class Approval {
    private String type;
    private Authorization authorization;

    public Approval() {
    }

    public String getType() {
        return type;
    }

    public Approval setType(String type) {
        this.type = type;
        return this;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public Approval setAuthorization(Authorization authorization) {
        this.authorization = authorization;
        return this;
    }
}
