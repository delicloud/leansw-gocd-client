package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BuildCause {

    private String approver;

    @JsonProperty("material_revisions")
    private List<MaterialRevision> materialRevisions = new ArrayList<>();
    @JsonProperty("trigger_forced")
    private boolean triggerForced;
    @JsonProperty("trigger_message")
    private String triggerMessage;

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public List<MaterialRevision> getMaterialRevisions() {
        return materialRevisions;
    }

    public void setMaterialRevisions(List<MaterialRevision> materialRevisions) {
        this.materialRevisions = materialRevisions;
    }

    public boolean getTriggerForced() {
        return triggerForced;
    }

    public void setTriggerForced(boolean triggerForced) {
        this.triggerForced = triggerForced;
    }

    public String getTriggerMessage() {
        return triggerMessage;
    }

    public void setTriggerMessage(String triggerMessage) {
        this.triggerMessage = triggerMessage;
    }

    @Override
    public String toString() {
        return "BuildCause{" +
                "approver='" + approver + '\'' +
                ", materialRevisions=" + materialRevisions +
                ", triggerForced=" + triggerForced +
                ", triggerMessage='" + triggerMessage + '\'' +
                '}';
    }
}
