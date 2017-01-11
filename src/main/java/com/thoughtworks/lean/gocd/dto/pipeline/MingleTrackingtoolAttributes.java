package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class MingleTrackingtoolAttributes implements TrackingtoolAttributes {

    @JsonProperty("base_url")
    private String baseUrl;

    @JsonProperty("project_identifier")
    private String projectIdentifier;

    @JsonProperty("mql_grouping_conditions")
    private String mqlGroupingConditions;

    public MingleTrackingtoolAttributes() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public MingleTrackingtoolAttributes setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public MingleTrackingtoolAttributes setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
        return this;
    }

    public String getMqlGroupingConditions() {
        return mqlGroupingConditions;
    }

    public MingleTrackingtoolAttributes setMqlGroupingConditions(String mqlGroupingConditions) {
        this.mqlGroupingConditions = mqlGroupingConditions;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("baseUrl", baseUrl)
                .append("projectIdentifier", projectIdentifier)
                .append("mqlGroupingConditions", mqlGroupingConditions)
                .toString();
    }
}
