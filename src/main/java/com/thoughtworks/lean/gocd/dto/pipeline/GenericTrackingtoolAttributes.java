package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class GenericTrackingtoolAttributes implements  TrackingtoolAttributes{

    @JsonProperty("url_pattern")
    private String urlParttern;
    private String regex;

    public GenericTrackingtoolAttributes() {
    }

    public String getUrlParttern() {
        return urlParttern;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("urlParttern", urlParttern)
                .append("regex", regex)
                .toString();
    }
}
