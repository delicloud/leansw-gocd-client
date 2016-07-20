package com.thoughtworks.lean.gocd.dto.history;

import java.util.ArrayList;
import java.util.List;

public class PipelineHistoryResult {

    private List<PipelineHistory> pipelines = new ArrayList<>();
    private Pagination pagination;

    public List<PipelineHistory> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<PipelineHistory> pipelines) {
        this.pipelines = pipelines;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
