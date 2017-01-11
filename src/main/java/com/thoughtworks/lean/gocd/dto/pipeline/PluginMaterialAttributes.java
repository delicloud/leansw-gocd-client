package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class PluginMaterialAttributes implements MaterialAttributes {

    private String ref;

    private String destination;

    private Filter filter;

    @JsonProperty("invert_filter")
    private Boolean invertFilter = false;

    public PluginMaterialAttributes() {
    }

    public String getRef() {
        return ref;
    }

    public PluginMaterialAttributes setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public PluginMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public PluginMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public PluginMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ref", ref)
                .append("destination", destination)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .toString();
    }
}