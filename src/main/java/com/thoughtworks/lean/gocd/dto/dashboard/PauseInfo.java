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
}
