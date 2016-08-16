package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yongliuli on 8/16/16.
 */
public class Pipeline {

    private String name;
    private boolean locked;
    @JsonProperty("pause_info")
    private PauseInfo pauseInfo;
    @JsonProperty("_embedded")
    private EmbeddedInstances embedded;

    public String getName() {
        return name;
    }

    public Pipeline setName(String name) {
        this.name = name;
        return this;
    }

    public EmbeddedInstances getEmbedded() {
        return embedded;
    }

    public Pipeline setEmbedded(EmbeddedInstances embedded) {
        this.embedded = embedded;
        return this;
    }

    public PauseInfo getPauseInfo() {
        return pauseInfo;
    }

    public Pipeline setPauseInfo(PauseInfo pauseInfo) {
        this.pauseInfo = pauseInfo;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public Pipeline setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }
}
