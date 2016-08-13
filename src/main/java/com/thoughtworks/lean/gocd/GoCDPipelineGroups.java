package com.thoughtworks.lean.gocd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class GoCDPipelineGroups {

    private String name;

    private List<GOCDPipeline> pipelines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GOCDPipeline> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<GOCDPipeline> pipelines) {
        this.pipelines = pipelines;
    }

    @Override
    public String toString() {
        return "GoCDPipelineGroups{" +
                "name='" + name + '\'' +
                ", pipelines=" + pipelines +
                '}';
    }
}
