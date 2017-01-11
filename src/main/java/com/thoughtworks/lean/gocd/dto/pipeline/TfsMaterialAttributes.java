package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class TfsMaterialAttributes implements MaterialAttributes {

    private String name;

    private String url;

    @JsonProperty("project_path")
    private String projectPath;

    private String domain;

    private String username;

    private String password;

    @JsonProperty("encrypted_password")
    private String encryptedPassword;

    private String destination;

    @JsonProperty("auto_update")
    private Boolean autoUpdate = true;

    private Filter filter;

    @JsonProperty("invert_filter")
    private Boolean invertFilter = false;

    public TfsMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public TfsMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TfsMaterialAttributes setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public TfsMaterialAttributes setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public TfsMaterialAttributes setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TfsMaterialAttributes setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public TfsMaterialAttributes setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public TfsMaterialAttributes setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public TfsMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public TfsMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public TfsMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public TfsMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("url", url)
                .append("projectPath", projectPath)
                .append("domain", domain)
                .append("username", username)
                .append("password", password)
                .append("encryptedPassword", encryptedPassword)
                .append("destination", destination)
                .append("autoUpdate", autoUpdate)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .toString();
    }
}

