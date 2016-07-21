package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;

public class Job {

    public static final String CRUISE_JOB_DURATION = "cruise_job_duration";

    private String name;
    private String result;
    private String state;
    private int id;
    @JsonProperty("scheduled_date")
    private Date scheduledDate;
    private Map<String, String> properties = Maps.newHashMap();
    private transient Date completeDate;
    private transient int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public int getDuration() {
        return duration;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void caculateDuration() {
        final String durationString = this.getProperties().get(Job.CRUISE_JOB_DURATION);
        duration = StringUtils.isNotBlank(durationString) ? Integer.parseInt(durationString) : 0;
    }

    public void caculateCompleteTime() {
        if (null == completeDate) {
            completeDate = new DateTime(getScheduledDate().getTime() + getDuration()).toDate();
        }
    }
}
