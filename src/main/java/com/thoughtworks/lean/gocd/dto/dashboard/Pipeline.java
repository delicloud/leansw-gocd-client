package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

public class Pipeline {

    private String name;
    private boolean locked;
    @JsonProperty("pause_info")
    private PauseInfo pauseInfo;

    Collection<PipelineInstance> instances;

    public Pipeline() {
    }

    @JsonProperty("_embedded")
    private void unpackObjectsFromEmbededObject(Map<String, Collection<PipelineInstance>> embeded) {
        instances = embeded.get("instances");
    }

    public String getName() {
        return name;
    }

    public Pipeline setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<PipelineInstance> getInstances() {
        return instances;
    }

    public Pipeline setInstances(Collection<PipelineInstance> instances) {
        this.instances = instances;
        return this;
    }

    public Pipeline(String name) {
        this.name = name;
    }

    public boolean isLocked() {
        return locked;
    }

    public Pipeline setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public PauseInfo getPauseInfo() {
        return pauseInfo;
    }

    public Pipeline setPauseInfo(PauseInfo pauseInfo) {
        this.pauseInfo = pauseInfo;
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
        return instances != null ? instances.equals(pipeline.instances) : pipeline.instances == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (locked ? 1 : 0);
        result = 31 * result + (pauseInfo != null ? pauseInfo.hashCode() : 0);
        result = 31 * result + (instances != null ? instances.hashCode() : 0);
        return result;
    }
}
