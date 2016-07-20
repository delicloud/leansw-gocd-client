package com.thoughtworks.lean.gocd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentsInfoResponse {
    @JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public AgentsInfoResponse setEmbedded(Embedded embedded) {
        this.embedded = embedded;
        return this;
    }
}
