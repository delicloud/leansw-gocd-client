package com.thoughtworks.lean.gocd.dto.pipeline;

public class GitMaterialAttributes implements Attributes{

    private String name;
    private String url;
    private String branch;
    private String destination;
    private Boolean auto_update=true;
    private Filter filter;
    private String submodule_folder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getAuto_update() {
        return auto_update;
    }

    public void setAuto_update(Boolean auto_update) {
        this.auto_update = auto_update;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String getSubmodule_folder() {
        return submodule_folder;
    }

    public void setSubmodule_folder(String submodule_folder) {
        this.submodule_folder = submodule_folder;
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
