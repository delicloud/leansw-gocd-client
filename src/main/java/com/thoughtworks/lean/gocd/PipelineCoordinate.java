package com.thoughtworks.lean.gocd;

/**
 * Created by yongliuli on 8/13/16.
 */
public class PipelineCoordinate {
    int groupIndex;
    int pipelineIndexInGroup;

    public int getGroupIndex() {
        return groupIndex;
    }

    public PipelineCoordinate setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
        return this;
    }

    public int getPipelineIndexInGroup() {
        return pipelineIndexInGroup;
    }

    public PipelineCoordinate setPipelineIndexInGroup(int pipelineIndexInGroup) {
        this.pipelineIndexInGroup = pipelineIndexInGroup;
        return this;
    }
}
