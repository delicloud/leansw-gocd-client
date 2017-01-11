package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class MercurialMaterialAttributes implements MaterialAttributes{

    private String name;

    private String url;

    private String destination;

    private Filter filter;

    @JsonProperty("invert_filter")
    private Boolean invertFilter = false;

    @JsonProperty("auto_update")
    private Boolean autoUpdate = true;

    public MercurialMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public MercurialMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MercurialMaterialAttributes setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public MercurialMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public MercurialMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public MercurialMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public MercurialMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("url", url)
                .append("destination", destination)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .append("autoUpdate", autoUpdate)
                .toString();
    }
}
