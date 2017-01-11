package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class SvnMaterialAttributes implements MaterialAttributes {

    private String name;

    private String url;

    private String username;

    private String password;

    @JsonProperty("encrypted_password")
    private String encryptedPassword;

    private String destination;

    private Filter filter;

    @JsonProperty("invert_filter")
    private Boolean invertFilter = false;

    @JsonProperty("auto_update")
    private Boolean autoUpdate = true;

    @JsonProperty("check_externals")
    private Boolean checkExternals;

    public SvnMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public SvnMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SvnMaterialAttributes setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SvnMaterialAttributes setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SvnMaterialAttributes setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public SvnMaterialAttributes setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public SvnMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public SvnMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public SvnMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public SvnMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    public Boolean getCheckExternals() {
        return checkExternals;
    }

    public SvnMaterialAttributes setCheckExternals(Boolean checkExternals) {
        this.checkExternals = checkExternals;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("url", url)
                .append("username", username)
                .append("password", password)
                .append("encryptedPassword", encryptedPassword)
                .append("destination", destination)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .append("autoUpdate", autoUpdate)
                .append("checkExternals", checkExternals)
                .toString();
    }
}
