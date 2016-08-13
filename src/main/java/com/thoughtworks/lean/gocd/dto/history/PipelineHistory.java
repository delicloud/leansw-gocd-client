package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.lean.gocd.util.DateUtil;

import java.util.*;

import static com.thoughtworks.lean.gocd.dto.history.BuildStatus.Passed;
import static com.thoughtworks.lean.gocd.dto.history.BuildStatus.Unknown;
import static com.thoughtworks.lean.gocd.util.CollectionsUtil.sumInt;
import static com.thoughtworks.lean.gocd.util.CollectionsUtil.sumLong;

public class PipelineHistory {
    private int id;
    @JsonProperty("build_cause")
    protected BuildCause buildCause;
    protected String name;
    @JsonProperty("natural_order")
    protected Integer naturalOrder;
    @JsonProperty("can_run")
    protected boolean canRun;
    protected String comment;
    protected List<Stage> stages = new ArrayList<>();
    protected int counter;

    @JsonProperty("preparing_to_schedule")
    protected boolean preparingToSchedule;
    protected String label;

    protected Date scheduledDate;
    protected transient Date completeTime;
    protected transient long duration;
    protected transient int commitsCount;

    private boolean caculated;

    private String result;

    private Map<String, String> pullRequestData;

    public BuildCause getBuildCause() {
        return buildCause;
    }

    public PipelineHistory setBuildCause(BuildCause buildCause) {
        this.buildCause = buildCause;
        return this;
    }

    public boolean isCanRun() {
        return canRun;
    }

    public PipelineHistory setCanRun(boolean canRun) {
        this.canRun = canRun;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PipelineHistory setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getCommitsCount() {
        return commitsCount;
    }

    public PipelineHistory setCommitsCount(Integer commitsCount) {
        this.commitsCount = commitsCount;
        return this;
    }

    public int getCounter() {
        return counter;
    }

    public PipelineHistory setCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public PipelineHistory setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public int getId() {
        return id;
    }

    public PipelineHistory setId(int id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public PipelineHistory setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getName() {
        return name;
    }

    public PipelineHistory setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNaturalOrder() {
        return naturalOrder;
    }

    public PipelineHistory setNaturalOrder(Integer naturalOrder) {
        this.naturalOrder = naturalOrder;
        return this;
    }

    public boolean isPreparingToSchedule() {
        return preparingToSchedule;
    }

    public PipelineHistory setPreparingToSchedule(boolean preparingToSchedule) {
        this.preparingToSchedule = preparingToSchedule;
        return this;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public PipelineHistory setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
        return this;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public PipelineHistory setStages(List<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, String> getPullRequestData() {
        return pullRequestData;
    }

    public void setPullRequestData(Map<String, String> pullRequestData) {
        this.pullRequestData = pullRequestData;
    }

    public long getDuration() {
        return duration;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public boolean isCaculated() {
        return caculated;
    }

    public PipelineHistory setCommitCount(int commitCount) {
        this.commitsCount = commitCount;
        return this;
    }

    public PipelineHistory setCaculated(boolean caculated) {
        this.caculated = caculated;
        return this;
    }


    public void caculateProps() {
        if (isCaculated()){
            return;
        }
        List<Stage> stages = getStages();

        stages.forEach(stage -> {
            stage.caculateDuration();
            stage.caculateCompleteTime();
        });
        if (!stages.isEmpty() && !stages.get(0).getJobs().isEmpty()) {
            setScheduledDate(stages.get(0).getJobs().get(0).getScheduledDate());
            final Optional<Stage> stage = getStages().stream().filter(history -> !Passed.name().equalsIgnoreCase(history.getResult())).findFirst();
            if (stage.isPresent()) {
                setResult(stage.get().getResult() == null ? Unknown.name() : stage.get().getResult());
            } else {
                setResult(Passed.name());
            }
        }
        setDuration(sumLong(stages, Stage::getDuration));
        setCompleteTime(DateUtil.maxOrNull(stages.stream().map(Stage::getCompleteTime)));
        setCommitsCount(sumInt(getBuildCause().getMaterialRevisions(), e -> e.getModifications().size()));
        setCaculated(true);
    }
    @Override
    public String toString() {
        return "PipelineHistory{" +
                "buildCause=" + buildCause +
                ", name='" + name + '\'' +
                ", naturalOrder=" + naturalOrder +
                ", canRun=" + canRun +
                ", comment='" + comment + '\'' +
                ", stages=" + stages +
                ", counter=" + counter +
                ", id=" + id +
                ", preparingToSchedule=" + preparingToSchedule +
                ", label='" + label + '\'' +
                ", scheduledDate=" + scheduledDate +
                ", result='" + result + '\'' +
                '}';
    }

}
