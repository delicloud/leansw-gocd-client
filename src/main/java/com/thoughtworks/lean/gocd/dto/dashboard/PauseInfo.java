package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PauseInfo {
    private Boolean paused;
    @JsonProperty("paused_by")
    private String pausedBy;
    @JsonProperty("pause_reason")
    private String pauseReason;

    public Boolean isPaused() {
        return paused;
    }

    public PauseInfo setPaused(Boolean paused) {
        this.paused = paused;
        return this;
    }

    public String getPausedBy() {
        return pausedBy;
    }

    public PauseInfo setPausedBy(String pausedBy) {
        this.pausedBy = pausedBy;
        return this;
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public PauseInfo setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PauseInfo pauseInfo = (PauseInfo) o;

        if (paused != pauseInfo.paused) return false;
        if (pausedBy != null ? !pausedBy.equals(pauseInfo.pausedBy) : pauseInfo.pausedBy != null) return false;
        return pauseReason != null ? pauseReason.equals(pauseInfo.pauseReason) : pauseInfo.pauseReason == null;

    }

    @Override
    public int hashCode() {
        int result = (paused ? 1 : 0);
        result = 31 * result + (pausedBy != null ? pausedBy.hashCode() : 0);
        result = 31 * result + (pauseReason != null ? pauseReason.hashCode() : 0);
        return result;
    }
}
