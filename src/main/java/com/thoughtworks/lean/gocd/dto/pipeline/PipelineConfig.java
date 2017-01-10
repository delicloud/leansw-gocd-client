package com.thoughtworks.lean.gocd.dto.pipeline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class PipelineConfig implements Serializable {


    private long id;

    private String name;

    private String groupName;

    private List<Material> materials;

    private List<Stage> stages;

    private List<EnvironmentVariables> environment_variables;

    private boolean locked;
    private boolean paused;
    private boolean schedulable;

    private boolean enable_pipeline_locking;
    private Timer timer;

    private Date createDate;

    private String settingsPath;


    public long getId() {
        return id;
    }

    public PipelineConfig setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PipelineConfig setName(String name) {
        this.name = name;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public PipelineConfig setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public PipelineConfig setMaterials(List<Material> materials) {
        this.materials = materials;
        return this;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public PipelineConfig setStages(List<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public PipelineConfig setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public boolean isPaused() {
        return paused;
    }

    public PipelineConfig setPaused(boolean paused) {
        this.paused = paused;
        return this;
    }

    public boolean isSchedulable() {
        return schedulable;
    }

    public PipelineConfig setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
        return this;
    }

    public Timer getTimer() {
        return timer;
    }

    public PipelineConfig setTimer(Timer timer) {
        this.timer = timer;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public PipelineConfig setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getSettingsPath() {
        return settingsPath;
    }

    public PipelineConfig setSettingsPath(String settingsPath) {
        this.settingsPath = settingsPath;
        return this;
    }

    public List<EnvironmentVariables> getEnvironment_variables() {
        return environment_variables;
    }

    public PipelineConfig setEnvironment_variables(List<EnvironmentVariables> environment_variables) {
        this.environment_variables = environment_variables;
        return this;
    }

    public boolean isEnable_pipeline_locking() {
        return enable_pipeline_locking;
    }

    public PipelineConfig setEnable_pipeline_locking(boolean enable_pipeline_locking) {
        this.enable_pipeline_locking = enable_pipeline_locking;
        return this;
    }
}
