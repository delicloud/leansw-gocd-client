package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.Map;

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

    @JsonProperty("_links")
    Map<String,Link> links;


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
                .append(embedded, that.embedded)
                .append(links, that.links)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(label)
                .append(scheduleAt)
                .append(triggeredBy)
                .append(embedded)
                .append(links)
                .toHashCode();
    }
}
