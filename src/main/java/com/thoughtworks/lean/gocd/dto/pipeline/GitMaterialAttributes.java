package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class GitMaterialAttributes implements MaterialAttributes {

    private String name;

    private String url;

    private String branch;

    private String destination;

    @JsonProperty("auto_update")
    private Boolean autoUpdate = true;

    private Filter filter;

    @JsonProperty("invert_filter")
    private Boolean invertFilter = false;

    @JsonProperty("submodule_folder")
    private String submoduleFolder;

    @JsonProperty("shallow_clone")
    private Boolean shallowClone = false;

    public GitMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public GitMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GitMaterialAttributes setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBranch() {
        return branch;
    }

    public GitMaterialAttributes setBranch(String branch) {
        this.branch = branch;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public GitMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public GitMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public GitMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public GitMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    public String getSubmoduleFolder() {
        return submoduleFolder;
    }

    public GitMaterialAttributes setSubmoduleFolder(String submoduleFolder) {
        this.submoduleFolder = submoduleFolder;
        return this;
    }

    public Boolean getShallowClone() {
        return shallowClone;
    }

    public GitMaterialAttributes setShallowClone(Boolean shallowClone) {
        this.shallowClone = shallowClone;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("url", url)
                .append("branch", branch)
                .append("destination", destination)
                .append("autoUpdate", autoUpdate)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .append("submoduleFolder", submoduleFolder)
                .append("shallowClone", shallowClone)
                .toString();
    }
}
