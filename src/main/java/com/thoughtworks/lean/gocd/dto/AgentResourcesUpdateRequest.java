package com.thoughtworks.lean.gocd.dto;

import java.util.Set;

public class AgentResourcesUpdateRequest {
    private Set<String> resources;

    public Set<String> getResources() {
        return resources;
    }

    public AgentResourcesUpdateRequest setResources(Set<String> resources) {
        this.resources = resources;
        return this;
    }
}
