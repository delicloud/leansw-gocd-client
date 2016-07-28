package com.thoughtworks.lean.gocd.dto;

public class PipelineStatus {
    private boolean locked;
    private boolean paused;
    private boolean schedulable;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isSchedulable() {
        return schedulable;
    }

    public void setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
    }
}
