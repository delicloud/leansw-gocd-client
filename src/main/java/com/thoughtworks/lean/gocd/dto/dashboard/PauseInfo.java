package com.thoughtworks.lean.gocd.dto.dashboard;

/**
 * Created by yongliuli on 8/16/16.
 */
public class PauseInfo {
    private boolean paused;
    private String pausedBy;
    private String pauseReason;

    public boolean isPaused() {
        return paused;
    }

    public PauseInfo setPaused(boolean paused) {
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
