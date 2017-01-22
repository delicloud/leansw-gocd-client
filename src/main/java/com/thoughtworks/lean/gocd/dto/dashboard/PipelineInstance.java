package com.thoughtworks.lean.gocd.dto.dashboard;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PipelineInstance {

    private String label;
    @JsonProperty("schedule_at")
    private Date scheduleAt;
    @JsonProperty("triggered_by")
    private String triggeredBy;
    @JsonProperty("_links")
    Map<String,Link> links;

    private List<Stage> stages;

    public PipelineInstance() {
    }

    @JsonProperty("_embedded")
    private void unpackObjectsFromEmbededObject(Map<String, List<Stage>> embeded) {
        stages = embeded.get("stages");
    }

    public List<Stage> getStages() {
        return stages;
    }

    public PipelineInstance setStages(List<Stage> stages) {
        this.stages = stages;
        return this;
    }

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

    public Map<String, Link> getLinks() {
        return links;
    }

    public PipelineInstance setLinks(Map<String, Link> links) {
        this.links = links;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PipelineInstance that = (PipelineInstance) o;

        return new EqualsBuilder()
                .append(label, that.label)
                .append(scheduleAt, that.scheduleAt)
                .append(triggeredBy, that.triggeredBy)
                .append(stages, that.stages)
                .append(links, that.links)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(label)
                .append(scheduleAt)
                .append(triggeredBy)
                .append(stages)
                .append(links)
                .toHashCode();
    }
}
