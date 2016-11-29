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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pipeline pipeline = (Pipeline) o;

        if (locked != pipeline.locked) return false;
        if (name != null ? !name.equals(pipeline.name) : pipeline.name != null) return false;
        if (pauseInfo != null ? !pauseInfo.equals(pipeline.pauseInfo) : pipeline.pauseInfo != null) return false;
        return embedded != null ? embedded.equals(pipeline.embedded) : pipeline.embedded == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (locked ? 1 : 0);
        result = 31 * result + (pauseInfo != null ? pauseInfo.hashCode() : 0);
        result = 31 * result + (embedded != null ? embedded.hashCode() : 0);
        return result;
    }
}
