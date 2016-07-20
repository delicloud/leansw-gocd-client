package com.thoughtworks.lean.gocd.dto.history;


import org.joda.time.DateTime;

public class JobProperties {
    private String cruise_agent;
    private long cruise_job_duration;
    private int cruise_job_id;
    private String cruise_job_result;
    private int cruise_pipeline_counter;
    private String cruise_pipeline_label;
    private int cruise_stage_counter;
    private DateTime cruise_timestamp_01_scheduled;
    private DateTime cruise_timestamp_02_assigned;
    private DateTime cruise_timestamp_03_preparing;
    private DateTime cruise_timestamp_04_building;
    private DateTime cruise_timestamp_05_completing;
    private DateTime cruise_timestamp_06_completed;

    public String getCruise_agent() {
        return cruise_agent;
    }

    public void setCruise_agent(String cruise_agent) {
        this.cruise_agent = cruise_agent;
    }

    public long getCruise_job_duration() {
        return cruise_job_duration;
    }

    public void setCruise_job_duration(long cruise_job_duration) {
        this.cruise_job_duration = cruise_job_duration;
    }

    public int getCruise_job_id() {
        return cruise_job_id;
    }

    public void setCruise_job_id(int cruise_job_id) {
        this.cruise_job_id = cruise_job_id;
    }

    public String getCruise_job_result() {
        return cruise_job_result;
    }

    public void setCruise_job_result(String cruise_job_result) {
        this.cruise_job_result = cruise_job_result;
    }

    public int getCruise_pipeline_counter() {
        return cruise_pipeline_counter;
    }

    public void setCruise_pipeline_counter(int cruise_pipeline_counter) {
        this.cruise_pipeline_counter = cruise_pipeline_counter;
    }

    public String getCruise_pipeline_label() {
        return cruise_pipeline_label;
    }

    public void setCruise_pipeline_label(String cruise_pipeline_label) {
        this.cruise_pipeline_label = cruise_pipeline_label;
    }

    public int getCruise_stage_counter() {
        return cruise_stage_counter;
    }

    public void setCruise_stage_counter(int cruise_stage_counter) {
        this.cruise_stage_counter = cruise_stage_counter;
    }

    public DateTime getCruise_timestamp_01_scheduled() {
        return cruise_timestamp_01_scheduled;
    }

    public void setCruise_timestamp_01_scheduled(DateTime cruise_timestamp_01_scheduled) {
        this.cruise_timestamp_01_scheduled = cruise_timestamp_01_scheduled;
    }

    public DateTime getCruise_timestamp_02_assigned() {
        return cruise_timestamp_02_assigned;
    }

    public void setCruise_timestamp_02_assigned(DateTime cruise_timestamp_02_assigned) {
        this.cruise_timestamp_02_assigned = cruise_timestamp_02_assigned;
    }

    public DateTime getCruise_timestamp_03_preparing() {
        return cruise_timestamp_03_preparing;
    }

    public void setCruise_timestamp_03_preparing(DateTime cruise_timestamp_03_preparing) {
        this.cruise_timestamp_03_preparing = cruise_timestamp_03_preparing;
    }

    public DateTime getCruise_timestamp_04_building() {
        return cruise_timestamp_04_building;
    }

    public void setCruise_timestamp_04_building(DateTime cruise_timestamp_04_building) {
        this.cruise_timestamp_04_building = cruise_timestamp_04_building;
    }

    public DateTime getCruise_timestamp_05_completing() {
        return cruise_timestamp_05_completing;
    }

    public void setCruise_timestamp_05_completing(DateTime cruise_timestamp_05_completing) {
        this.cruise_timestamp_05_completing = cruise_timestamp_05_completing;
    }

    public DateTime getCruise_timestamp_06_completed() {
        return cruise_timestamp_06_completed;
    }

    public void setCruise_timestamp_06_completed(DateTime cruise_timestamp_06_completed) {
        this.cruise_timestamp_06_completed = cruise_timestamp_06_completed;
    }
}
