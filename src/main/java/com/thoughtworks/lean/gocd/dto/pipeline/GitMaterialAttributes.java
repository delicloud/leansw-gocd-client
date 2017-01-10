package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class GitMaterialAttributes implements Attributes{

    private String name;
    private String url;
    private String branch;
    private String destination;
    private Boolean auto_update=true;
    private Filter filter;
    private String submodule_folder;
    private Boolean invert_filter=false;
    private Boolean shallow_clone=false;

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

    public Boolean getAuto_update() {
        return auto_update;
    }

    public GitMaterialAttributes setAuto_update(Boolean auto_update) {
        this.auto_update = auto_update;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public GitMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public String getSubmodule_folder() {
        return submodule_folder;
    }

    public GitMaterialAttributes setSubmodule_folder(String submodule_folder) {
        this.submodule_folder = submodule_folder;
        return this;
    }

    public Boolean getInvert_filter() {
        return invert_filter;
    }

    public GitMaterialAttributes setInvert_filter(Boolean invert_filter) {
        this.invert_filter = invert_filter;
        return this;
    }

    public Boolean getShallow_clone() {
        return shallow_clone;
    }

    public GitMaterialAttributes setShallow_clone(Boolean shallow_clone) {
        this.shallow_clone = shallow_clone;
        return this;
    }

    @Override
    public String toString() {
        return "GitMaterialAttributes{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", branch='" + branch + '\'' +
                ", destination='" + destination + '\'' +
                ", auto_update=" + auto_update +
                ", filter=" + filter +
                ", submodule_folder='" + submodule_folder + '\'' +
                '}';
    }
}
