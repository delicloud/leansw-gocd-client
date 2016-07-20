package com.thoughtworks.lean.gocd.dto;

import java.util.List;

public class Embedded {
    private List<AgentInfo> agents;

    public List<AgentInfo> getAgents() {
        return agents;
    }

    public Embedded setAgents(List<AgentInfo> agents) {
        this.agents = agents;
        return this;
    }
}
