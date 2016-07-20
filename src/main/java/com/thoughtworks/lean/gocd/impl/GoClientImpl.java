package com.thoughtworks.lean.gocd.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistory;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistoryResult;
import com.thoughtworks.lean.exception.ParseException;
import com.thoughtworks.lean.gocd.GoClient;
import com.thoughtworks.lean.gocd.dto.AgentInfo;
import com.thoughtworks.lean.gocd.dto.AgentResourcesUpdateRequest;
import com.thoughtworks.lean.gocd.dto.AgentStatusUpdateRequest;
import com.thoughtworks.lean.gocd.dto.AgentsInfoResponse;
import com.thoughtworks.lean.util.NumberUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpMethod.*;

@Component
public class GoClientImpl implements GoClient {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(GoClient.class);

    private static final String CONFIG_FILE = "/api/admin/config.xml";

    private static final String ADMIN_PIPELINES = "/api/admin/pipelines";
    private static final String PIPELINES = "/api/pipelines";

    private static final String LOG_FILES = "/files";


    private String baseURI;

    private String username;

    private String password;

    private RestTemplate restTemplate;

    private int TIMEOUT = 5000;

    @Autowired
    public GoClientImpl(
            @Value("${gocd.uri}") String baseURL, @Value("${gocd.username}") String username,
            @Value("${gocd.password}") String password) {
        this.baseURI = baseURL;
        this.username = username;
        this.password = password;
        this.restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    public boolean schedule(String pipeline) {
        return pipelineAction(pipeline, "schedule");
    }

    @Override
    public boolean pause(String pipeName) {
        return pipelineAction(pipeName, "pause");
    }

    @Override
    public boolean resume(String pipeName) {
        return pipelineAction(pipeName, "unpause");
    }

    @Override
    public boolean manualRunPipelineStage(String pipeName, int counter, String stageName) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        String apiUrl = baseURI + "/run/" + pipeName + "/" + counter + "/" + stageName;
        new RestTemplate().exchange(apiUrl, POST, request, String.class);
        return true;
    }

    private boolean pipelineAction(String pipeline, String action) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        String url = baseURI + PIPELINES + "/" + pipeline + "/" + action;
        new RestTemplate().exchange(url, POST, request, String.class);
        return true;
    }

    @Override
    public String fetchCruiseConfiguration() {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        String requestUrl = String.format("%s%s", baseURI, CONFIG_FILE);
        ResponseEntity<String> response = new RestTemplate().exchange(requestUrl, HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    @Override
    public String fetchCruiseLog(String pipelineName, int pipelineCounter, String stageName, int stageCounter, String jobName) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        String requestUrl = String.format("%s%s/%s/%d/%s/%d/%s/cruise-output/console.log", baseURI, LOG_FILES, pipelineName, pipelineCounter, stageName, stageCounter, jobName);
        ResponseEntity<String> response = new RestTemplate().exchange(requestUrl, HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    @Override
    @Cacheable("gocd_pipelineHistoryResult")
    public PipelineHistoryResult getPipelineHistory(String pipelineName, int offset) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        String requestUrl = String.format("%s%s/%s/history/%s", baseURI, PIPELINES, pipelineName, offset);
        ResponseEntity<PipelineHistoryResult> response = new RestTemplate().exchange(requestUrl, HttpMethod.GET, request, PipelineHistoryResult.class);
        return response.getBody();
    }

    public String getPipelineConfiguration(String pipelineName) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders("v1"));
        ResponseEntity<String> response = new RestTemplate().exchange(baseURI + ADMIN_PIPELINES + "/" + pipelineName, GET, request, String.class);
        return response.getBody();
    }

    @Override
    @Cacheable("gocd_pipelineInstance")
    public Map getPipelineInstance(String pipelineName, String counter) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        final String requestUrl = String.format("%s%s/%s/instance/%s", baseURI, PIPELINES, pipelineName, counter);
        ResponseEntity<HashMap> response = new RestTemplate().exchange(requestUrl, GET, request, HashMap.class);
        return response.getBody();
    }

    @Override
    @Cacheable("gocd_pipelineHistory")
    public PipelineHistory getPipelineInstance(String pipelineName, int counter) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        final String requestUrl = String.format("%s%s/%s/instance/%s", baseURI, PIPELINES, pipelineName, counter);
        ResponseEntity<PipelineHistory> response = new RestTemplate().exchange(requestUrl, GET, request, PipelineHistory.class);
        return response.getBody();
    }


    @Override
    public Map<String, String> fetchJobProperties(String pipeline, int pipelineCounter, String stage, int stageCounter, String job) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders());
        final UriComponents requestUri = UriComponentsBuilder.fromUriString(String.format("%s%s", baseURI, "/properties/{pipelineName}/{pipelineCounter}/{stageName}/{stageCounter}/{jobName}")
                .replace("{pipelineName}", pipeline)
                .replace("{pipelineCounter}", Integer.toString(pipelineCounter))
                .replace("{stageName}", stage)
                .replace("{stageCounter}", Integer.toString(stageCounter))
                .replace("{jobName}", job)).build();
        try {
            ResponseEntity<String> response = new RestTemplate().exchange(requestUri.toUriString(), GET, request, String.class);
            return parseJobCsv(response);
        } catch (RestClientException e) {
            LOG.error(String.format("error fetching properties:%s", requestUri.toUriString()), e);
            return Maps.newHashMap();
        }
    }

    private Map<String, String> parseJobCsv(ResponseEntity<String> response) {
        try (CSVReader csvReader = new CSVReader(new StringReader(response.getBody()))) {
            final List<String[]> lines = csvReader.readAll();
            final String[] headers = lines.get(0);
            final String[] datas = lines.get(1);
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            NumberUtil.intRange(0, headers.length - 1).stream().forEach(
                    i -> builder.put(headers[i], datas[i])
            );
            return builder.build();
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }


    @Override
    public AgentsInfoResponse fetchAllAgents() {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders("v2"));
        ResponseEntity<AgentsInfoResponse> response = this.restTemplate.exchange(baseURI + "/api/agents", GET, request, AgentsInfoResponse.class);
        return response.getBody();
    }

    @Override
    public AgentInfo getAgent(String uuid) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders("v2"));
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + uuid, GET, request, AgentInfo.class);
        return response.getBody();
    }

    @Override
    public AgentInfo addResourcesToAgent(String uuid, Set<String> resources) {
        AgentInfo agentInfo = getAgent(uuid);
        agentInfo.getResources().addAll(resources);
        return updateAgentResources(agentInfo);
    }

    private AgentInfo updateAgentResources(AgentInfo agentInfo) {
        AgentResourcesUpdateRequest req = new AgentResourcesUpdateRequest()
                .setResources(agentInfo.getResources());
        HttpEntity<AgentResourcesUpdateRequest> request = new HttpEntity<>(req, buildHttpHeaders("v2"));
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentInfo.getUuid(), PATCH, request, AgentInfo.class);
        return response.getBody();
    }


    @Override
    public AgentInfo delResourceFromAgent(String uuid, Set<String> resources) {
        AgentInfo agentInfo = getAgent(uuid);
        agentInfo.getResources().removeAll(resources);
        return updateAgentResources(agentInfo);
    }


    private AgentInfo updateAgentStatus(String agentUUID, String status) {
        AgentStatusUpdateRequest req = new AgentStatusUpdateRequest()
                .setAgentConfigState(status);
        HttpEntity<AgentStatusUpdateRequest> request = new HttpEntity<>(req, buildHttpHeaders("v2"));
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentUUID, PATCH, request, AgentInfo.class);
        return response.getBody();
    }

    @Override
    public AgentInfo disableAgent(String agentUUID) {
        return updateAgentStatus(agentUUID, "Disabled");
    }

    @Override
    public AgentInfo enableAgent(String agentUUID) {
        return updateAgentStatus(agentUUID, "Enabled");
    }

    @Override
    public void deleteAgent(String agentNeedDelete) {
        HttpEntity<String> request = new HttpEntity<>(buildHttpHeaders("v2"));
        ResponseEntity<String> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentNeedDelete, DELETE, request, String.class);
        LOG.debug(response.getBody());
    }

    private HttpHeaders buildHttpHeaders() {
        byte[] plainCredsBytes = (this.username + ":" + this.password).getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64Creds);
        headers.set("charset", "utf-8");

        return headers;
    }

    private HttpHeaders buildHttpHeaders(String apiVersion) {

        HttpHeaders headers = buildHttpHeaders();
        if ("v1".equals(apiVersion)) {
            headers.set("Accept", "application/vnd.go.cd.v1+json");
        } else if ("v2".equals(apiVersion)) {
            headers.set("Accept", "application/vnd.go.cd.v2+json");
        } else {
            throw new RuntimeException(apiVersion + " version dose not match any go cd API");
        }

        return headers;
    }


}
