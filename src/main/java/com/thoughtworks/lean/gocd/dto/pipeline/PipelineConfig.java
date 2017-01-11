package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class PipelineConfig implements Serializable {

    private long id;

    @JsonProperty("label_template")
    private String labelTemplate;

    @JsonProperty("enable_pipeline_locking")
    private Boolean enablePipelineLocking;

    private String name;

    private String template;

    private List<Parameter> parameters;

    @JsonProperty("environment_variables")
    private List<EnvironmentVariable> environmentVariables;

    private List<Material> materials;

    private List<Stage> stages;

    @JsonProperty("tracking_tool")
    private Trackingtool trackingtool;

    private Timer timer;

    private boolean locked;
    private boolean paused;
    private boolean schedulable;
    private Date createDate;
    private String settingsPath;
    private String groupName;


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

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return environmentVariables;
    }

    public PipelineConfig setEnvironmentVariables(List<EnvironmentVariable> environmentVariables) {
        this.environmentVariables = environmentVariables;
        return this;
    }

    public String getLabelTemplate() {
        return labelTemplate;
    }

    public PipelineConfig setLabelTemplate(String labelTemplate) {
        this.labelTemplate = labelTemplate;
        return this;
    }

    public Boolean getEnablePipelineLocking() {
        return enablePipelineLocking;
    }

    public PipelineConfig setEnablePipelineLocking(Boolean enablePipelineLocking) {
        this.enablePipelineLocking = enablePipelineLocking;
        return this;
    }

    public String getTemplate() {
        return template;
    }

    public PipelineConfig setTemplate(String template) {
        this.template = template;
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public PipelineConfig setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Trackingtool getTrackingtool() {
        return trackingtool;
    }

    public PipelineConfig setTrackingtool(Trackingtool trackingtool) {
        this.trackingtool = trackingtool;
        return this;
    }
}
