package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stage {

    private String name;
    @JsonProperty("approved_by")
    private String approvedBy;
    private List<Job> jobs = new ArrayList<>();
    @JsonProperty("can_run")
    private boolean canRun;
    private String result;
    @JsonProperty("approval_type")
    private String approvalType;
    private int counter;
    private int id;
    @JsonProperty("operate_permission")
    private boolean operatePermission;
    @JsonProperty("rerun_of_counter")
    private Integer rerunOfCounter;
    private boolean scheduled;
    private transient Long duration;
    private transient Date completeTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public boolean getCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOperatePermission() {
        return operatePermission;
    }

    public void setOperatePermission(boolean operatePermission) {
        this.operatePermission = operatePermission;
    }

    public Integer getRerunOfCounter() {
        return rerunOfCounter;
    }

    public void setRerunOfCounter(Integer rerunOfCounter) {
        this.rerunOfCounter = rerunOfCounter;
    }

    public boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }


    public long getDuration() {
        return duration;
    }

    public Date getCompleteTime() {
        return completeTime;
    }


}
