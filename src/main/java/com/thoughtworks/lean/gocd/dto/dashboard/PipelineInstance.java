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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PipelineInstance that = (PipelineInstance) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (scheduleAt != null ? !scheduleAt.equals(that.scheduleAt) : that.scheduleAt != null) return false;
        if (triggeredBy != null ? !triggeredBy.equals(that.triggeredBy) : that.triggeredBy != null) return false;
        return embedded != null ? embedded.equals(that.embedded) : that.embedded == null;

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (scheduleAt != null ? scheduleAt.hashCode() : 0);
        result = 31 * result + (triggeredBy != null ? triggeredBy.hashCode() : 0);
        result = 31 * result + (embedded != null ? embedded.hashCode() : 0);
        return result;
    }
}
