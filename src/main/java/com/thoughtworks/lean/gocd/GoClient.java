package com.thoughtworks.lean.gocd;

import com.thoughtworks.lean.gocd.dto.AgentInfo;
import com.thoughtworks.lean.gocd.dto.AgentsInfoResponse;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistory;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistoryResult;

import java.util.Map;
import java.util.Set;

public interface GoClient {


    PipelineHistoryResult getPipelineHistory(String pipelineName, int offset);

    String getPipelineConfiguration(String pipelineName);

    Map getPipelineInstance(String pipelineName, String counter);

    PipelineHistory getPipelineInstance(String pipelineName, int counter);

    boolean schedule(String pipeline);

    String fetchCruiseConfiguration();

    String fetchCruiseLog(String pipelineName, int pipelineCounter, String stageName, int stageCounter, String jobName);

    Map<String, String> fetchJobProperties(String pipeline, int pipelineCounter, String stage, int stageCounter, String job);

    Map<String, String> fetchJobProperties(JobParams jobParams);

    boolean pause(String pipeName);

    boolean resume(String pipeName);

    boolean manualRunPipelineStage(String pipeName, int counter, String stageName);

    AgentsInfoResponse fetchAllAgents();

    AgentInfo getAgent(String uuid);

    AgentInfo addResourcesToAgent(String uuid, Set<String> resources);

    AgentInfo delResourceFromAgent(String uuid, Set<String> resources);

    AgentInfo disableAgent(String agentUUID);

    AgentInfo enableAgent(String agentUUID);

    void deleteAgent(String agentNeedDelete);

}
