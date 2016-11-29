package com.thoughtworks.lean.gocd.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by yongliuli on 8/16/16.
 */
public class Stage {
    String name;
    String status;
    @JsonProperty("_links")
    Map<String,Link> links;

    public String getName() {
        return name;
    }

    public Stage setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Stage setStatus(String status) {
        this.status = status;
        return this;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public Stage setLinks(Map<String, Link> links) {
        this.links = links;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage stage = (Stage) o;

        if (name != null ? !name.equals(stage.name) : stage.name != null) return false;
        if (status != null ? !status.equals(stage.status) : stage.status != null) return false;
        return links != null ? links.equals(stage.links) : stage.links == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }
}
