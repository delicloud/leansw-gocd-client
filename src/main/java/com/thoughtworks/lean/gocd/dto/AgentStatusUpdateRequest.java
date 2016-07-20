package com.thoughtworks.lean.gocd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentStatusUpdateRequest {
    @JsonProperty("agent_config_state")
    private String agentConfigState;

    public String getAgentConfigState() {
        return agentConfigState;
    }

    public AgentStatusUpdateRequest setAgentConfigState(String agentConfigState) {
        this.agentConfigState = agentConfigState;
        return this;
    }
}
