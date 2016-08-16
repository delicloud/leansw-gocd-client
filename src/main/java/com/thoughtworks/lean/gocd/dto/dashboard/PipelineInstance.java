package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yongliuli on 8/16/16.
 */
public class PipelineInstance {


    private String label;
    @JsonProperty("schedule_at")
    private Date scheduleAt;
    @JsonProperty("triggered_by")
    private String triggeredBy;
    @JsonProperty("_embedded")
    private EmbeddedStages embedded;

    public String getLabel() {
        return label;
    }

    public PipelineInstance setLabel(String label) {
        this.label = label;
        return this;
    }

    public Date getScheduleAt() {
        return scheduleAt;
    }

    public PipelineInstance setScheduleAt(Date scheduleAt) {
        this.scheduleAt = scheduleAt;
        return this;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }

    public PipelineInstance setTriggeredBy(String triggeredBy) {
        this.triggeredBy = triggeredBy;
        return this;
    }

    public EmbeddedStages getEmbedded() {
        return embedded;
    }

    public PipelineInstance setEmbedded(EmbeddedStages embedded) {
        this.embedded = embedded;
        return this;
    }
}
