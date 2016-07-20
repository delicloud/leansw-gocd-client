package com.thoughtworks.lean.gocd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class AgentInfo {
    private String uuid;
    private String hostname;
    private Set<String> resources;
    private Set<String> environments;
    @JsonProperty("agent_state")
    private String agentState;
    @JsonProperty("agent_config_state")
    private String agentConfigState;

    @JsonProperty("build_state")
    private String buildState;
    @JsonProperty("ip_address")
    private String ipAddress;

    public String getUuid() {
        return uuid;
    }

    public AgentInfo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getAgentConfigState() {
        return agentConfigState;
    }

    public AgentInfo setAgentConfigState(String agentConfigState) {
        this.agentConfigState = agentConfigState;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public AgentInfo setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public Set<String> getResources() {
        return resources;
    }

    public AgentInfo setResources(Set<String> resources) {
        this.resources = resources;
        return this;
    }

    public String getAgentState() {
        return agentState;
    }

    public AgentInfo setAgentState(String agentState) {
        this.agentState = agentState;
        return this;
    }

    public String getBuildState() {
        return buildState;
    }

    public AgentInfo setBuildState(String buildState) {
        this.buildState = buildState;
        return this;
    }

    public Set<String> getEnvironments() {
        return environments;
    }

    public AgentInfo setEnvironments(Set<String> environments) {
        this.environments = environments;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public AgentInfo setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    @Override
    public String toString() {
        return "AgentInfo{" +
                "agentConfigState='" + agentConfigState + '\'' +
                ", uuid='" + uuid + '\'' +
                ", hostname='" + hostname + '\'' +
                ", resources=" + resources +
                ", environments=" + environments +
                ", agentState='" + agentState + '\'' +
                ", buildState='" + buildState + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
