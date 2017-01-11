package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class PerforceMaterialAttributes implements MaterialAttributes{

    private String name;

    private String port;

    @JsonProperty("use_tickets")
    private Boolean useTickets;

    private String view;

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

    public PerforceMaterialAttributes() {
    }

    public String getName() {
        return name;
    }

    public PerforceMaterialAttributes setName(String name) {
        this.name = name;
        return this;
    }

    public String getPort() {
        return port;
    }

    public PerforceMaterialAttributes setPort(String port) {
        this.port = port;
        return this;
    }

    public Boolean getUseTickets() {
        return useTickets;
    }

    public PerforceMaterialAttributes setUseTickets(Boolean useTickets) {
        this.useTickets = useTickets;
        return this;
    }

    public String getView() {
        return view;
    }

    public PerforceMaterialAttributes setView(String view) {
        this.view = view;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PerforceMaterialAttributes setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PerforceMaterialAttributes setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public PerforceMaterialAttributes setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public PerforceMaterialAttributes setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public PerforceMaterialAttributes setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Boolean getInvertFilter() {
        return invertFilter;
    }

    public PerforceMaterialAttributes setInvertFilter(Boolean invertFilter) {
        this.invertFilter = invertFilter;
        return this;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public PerforceMaterialAttributes setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("port", port)
                .append("useTickets", useTickets)
                .append("view", view)
                .append("username", username)
                .append("password", password)
                .append("encryptedPassword", encryptedPassword)
                .append("destination", destination)
                .append("filter", filter)
                .append("invertFilter", invertFilter)
                .append("autoUpdate", autoUpdate)
                .toString();
    }
}
