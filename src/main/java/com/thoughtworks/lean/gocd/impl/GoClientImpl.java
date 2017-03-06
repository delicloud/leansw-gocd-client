package com.thoughtworks.lean.gocd.impl;

import com.google.common.collect.ImmutableMap;
import com.opencsv.CSVReader;
import com.thoughtworks.lean.exception.ParseException;
import com.thoughtworks.lean.gocd.GoClient;
import com.thoughtworks.lean.gocd.JobParams;
import com.thoughtworks.lean.gocd.dto.*;
import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistory;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistoryResult;
import com.thoughtworks.lean.gocd.dto.pipeline.*;
import com.thoughtworks.lean.gocd.util.NumberUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

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

    private int TIMEOUT = 5000;

    private RestTemplate restTemplate;

    public GoClientImpl(@Value("${gocd.uri}") String baseURL, @Value("${gocd.username}") String username,
                        @Value("${gocd.password}") String password) {
        this.baseURI = baseURL;
        this.username = username;
        this.password = password;
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
        String apiUrl = baseURI + "/run/" + pipeName + "/" + counter + "/" + stageName;
        restTemplate.exchange(apiUrl, POST, getStringRequest(), String.class);
        return true;
    }

    private boolean pipelineAction(String pipeline, String action) {
        String url = baseURI + PIPELINES + "/" + pipeline + "/" + action;
        restTemplate.exchange(url, POST, getStringRequestWithHeader("Confirm", "true"), String.class);
        return true;
    }

    @Override
    public DashBoard getDashBoard() {
        ResponseEntity<DashBoard> response = restTemplate.exchange(baseURI + "/api/dashboard", HttpMethod.GET, new HttpEntity<>(buildHttpHeaders("v1")), DashBoard.class);
        return response.getBody();
    }

    @Override
    public String fetchCruiseConfiguration() {
        String requestUrl = String.format("%s%s", baseURI, CONFIG_FILE);
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, getStringRequest(), String.class);
        return response.getBody();
    }

    @Override
    public String fetchCruiseLog(String pipelineName, int pipelineCounter, String stageName, int stageCounter, String jobName) {
        String requestUrl = String.format("%s%s/%s/%d/%s/%d/%s/cruise-output/console.log", baseURI, LOG_FILES, pipelineName, pipelineCounter, stageName, stageCounter, jobName);
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, getStringRequest(), String.class);
        return response.getBody();
    }

    @Override
    public PipelineHistoryResult getPipelineHistory(String pipelineName, int offset) {
        String requestUrl = String.format("%s%s/%s/history/%s", baseURI, PIPELINES, pipelineName, offset);
        ResponseEntity<PipelineHistoryResult> response = restTemplate.exchange(requestUrl, HttpMethod.GET, getStringRequest(), PipelineHistoryResult.class);
        response.getBody().getPipelines().forEach(this::completePipelineHistory);
        return response.getBody();
    }

    @Override
    public PipelineHistoryResult getPipelineHistory(String pipelineName, int offset, boolean complete) {
        String requestUrl = String.format("%s%s/%s/history/%s", baseURI, PIPELINES, pipelineName, offset);
        ResponseEntity<PipelineHistoryResult> response = restTemplate.exchange(requestUrl, HttpMethod.GET, getStringRequest(), PipelineHistoryResult.class);
        if (complete) {
            response.getBody().getPipelines().forEach(this::completePipelineHistory);
        }
        return response.getBody();
    }


    public void completePipelineHistory(PipelineHistory pipelineHistory) {
        this.updateJobProperties(pipelineHistory);
        pipelineHistory.caculateProps();
    }

    private void updateJobProperties(PipelineHistory pipelineHistory) {
        pipelineHistory.getStages().forEach(
                stage -> stage.getJobs().stream().filter(job -> job.getResult() != null)
                        .forEach(job -> job.setProperties(this.fetchJobProperties(
                                pipelineHistory.getName(),
                                pipelineHistory.getCounter(),
                                stage.getName(),
                                stage.getCounter(),
                                job.getName())
                        ))
        );
    }


    @Override
    @Cacheable("gocd_pipelineInstance")
    @Deprecated
    public Map getPipelineInstance(String pipelineName, String counter) {
        HttpEntity<String> request = getStringRequest();
        final String requestUrl = String.format("%s%s/%s/instance/%s", baseURI, PIPELINES, pipelineName, counter);
        ResponseEntity<HashMap> response = restTemplate.exchange(requestUrl, GET, request, HashMap.class);
        return response.getBody();
    }

    @Override
    @Cacheable("gocd_pipelineHistory")
    public PipelineHistory getPipelineInstance(String pipelineName, int counter) {
        return getPipelineInstance(pipelineName, counter, true);
    }

    @Override
    public PipelineHistory getPipelineInstance(String pipelineName, int counter, boolean complete) {
        final String requestUrl = String.format("%s%s/%s/instance/%s", baseURI, PIPELINES, pipelineName, counter);
        ResponseEntity<PipelineHistory> response = restTemplate.exchange(requestUrl, GET, getStringRequest(), PipelineHistory.class);
        if (complete) {
            this.completePipelineHistory(response.getBody());
        }
        return response.getBody();
    }

    @Override
    public Map<String, String> fetchJobProperties(JobParams jobParams) {
        Optional<Map<String, String>> ret = getJobPropsUriComponents(jobParams, "/properties/search?pipelineName={pipelineName}&stageName={stageName}&jobName={jobName}&limitPipeline={pipelineCounter}&limitCount=1");
        if (ret.isPresent()) {
            return ret.get();
        }
        return getJobPropsUriComponents(jobParams, "/properties/{pipelineName}/{pipelineCounter}/{stageName}/{stageCounter}/{jobName}")
                .orElse(new LinkedHashMap<>());
    }

    @Override
    public Map<String, String> fetchJobProperties(String pipeline, int pipelineCounter, String stage, int stageCounter, String job) {

        return fetchJobProperties(new JobParams(pipeline, pipelineCounter, stage, stageCounter, job));
    }

    private Optional<Map<String, String>> getJobPropsUriComponents(JobParams jobParams, String template) {
        HttpEntity<String> request = getStringRequest();
        final UriComponents requestUri = UriComponentsBuilder.fromUriString(String.format("%s%s", baseURI, template)
                .replace("{pipelineName}", jobParams.getPipeline())
                .replace("{pipelineCounter}", Integer.toString(jobParams.getPipelineCounter()))
                .replace("{stageName}", jobParams.getStage())
                .replace("{stageCounter}", Integer.toString(jobParams.getStageCounter()))
                .replace("{jobName}", jobParams.getJob())).build();
        try {
            ResponseEntity<String> response = restTemplate.exchange(requestUri.toUriString(), GET, request, String.class);
            LOG.info(String.format("Successfully fetched properties:%s", requestUri.toUriString()));
            return Optional.of(parseJobCsv(response));
        } catch (RestClientException e) {
            LOG.warn(String.format("failed in fetching properties:%s", requestUri.toUriString()), e);
        }
        return Optional.empty();
    }

    private Map<String, String> parseJobCsv(ResponseEntity<String> response) {
        try (CSVReader csvReader = new CSVReader(new StringReader(response.getBody()))) {
            final List<String[]> lines = csvReader.readAll();
            final String[] headers = lines.get(0);
            final String[] datas = lines.get(lines.size() - 1);
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            NumberUtil.intRange(0, headers.length - 1).forEach(
                    i -> builder.put(headers[i], datas[i])
            );
            return builder.build();
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }


    @Override
    public AgentsInfoResponse fetchAllAgents() {
        ResponseEntity<AgentsInfoResponse> response = this.restTemplate.exchange(baseURI + "/api/agents", GET, getV4Request(), AgentsInfoResponse.class);
        return response.getBody();
    }

    @Override
    public AgentInfo getAgent(String uuid) {
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + uuid, GET, getV2Request(), AgentInfo.class);
        return response.getBody();
    }

    @Override
    public AgentInfo addResourcesToAgent(String uuid, Set<String> resources) {
        AgentInfo agentInfo = getAgent(uuid);
        agentInfo.getResources().addAll(resources);
        return updateAgentResources(agentInfo);
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
        HttpEntity<AgentStatusUpdateRequest> request = getV2Request(req);
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentUUID, PATCH, request, AgentInfo.class);
        return response.getBody();
    }

    private AgentInfo updateAgentResources(AgentInfo agentInfo) {
        AgentResourcesUpdateRequest req = new AgentResourcesUpdateRequest()
                .setResources(agentInfo.getResources());
        HttpEntity<AgentResourcesUpdateRequest> request = getV2Request(req);
        ResponseEntity<AgentInfo> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentInfo.getUuid(), PATCH, request, AgentInfo.class);
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
        HttpEntity<String> request = getV2Request();
        ResponseEntity<String> response = this.restTemplate.exchange(baseURI + "/api/agents/" + agentNeedDelete, DELETE, request, String.class);
        LOG.debug(response.getBody());
    }


    @Override
    public PipelineStatus fetchPipelineStatus(String pipelineName) {
        ResponseEntity<PipelineStatus> response = this.restTemplate.exchange(baseURI + "/api/pipelines/" + pipelineName + "/status", GET, getStringRequest(), PipelineStatus.class);
        return response.getBody();
    }

    @Override
    public Collection<Template> getAllTemplates() {
        try {
            ParameterizedTypeReference<Resources<Template>> ptr = new ParameterizedTypeReference<Resources<Template>>() {
            };
            ResponseEntity<Resources<Template>> entity =
                    this.restTemplate.exchange(baseURI + "/api/admin/templates", GET, getV2Request(), ptr);
            return entity.getBody().getContent();
        } catch (HttpClientErrorException e) {
            LOG.error("Can not get all templates, will return an empty list. Message: {}, Response: {}.", e.getMessage(), e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }

    @Override
    public Template getTemplate(String name) {
        try {
            ParameterizedTypeReference<Resource<Template>> ptr = new ParameterizedTypeReference<Resource<Template>>() {
            };
            ResponseEntity<Resource<Template>> entity = this.restTemplate
                    .exchange(baseURI + "/api/admin/templates/" + name, GET, getV2Request(), ptr);
            return entity.getBody().getContent();
        } catch (HttpClientErrorException e) {
            LOG.error("Can not get Template: {}, Make sure it exists. Message: {}, Response: {}.",
                    name, e.getMessage(), e.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public PipelineConfig createPipelineFromTemplate(String pipelineName, String groupName, String templateName, String gitRepo, String branch) {
        Template template = this.getTemplate(templateName);
        PipelineConfig pipelineConfig = new PipelineConfig()
                .setName(pipelineName)
                .setLabelTemplate("${COUNT}")
                .setStages(template.getStages())
                .setMaterials(Arrays.asList(Material.gitRepo(gitRepo, branch)))
                .setTimer(null)
                .setEnvironmentVariables(Collections.emptyList())
                .setEnablePipelineLocking(false);
        PipelineCreate pipelineCreate = new PipelineCreate(groupName, pipelineConfig);

        try {
            ParameterizedTypeReference<Resource<PipelineConfig>> ptr = new ParameterizedTypeReference<Resource<PipelineConfig>>() {
            };
            ResponseEntity<Resource<PipelineConfig>> entity = this.restTemplate
                    .exchange(baseURI + "/api/admin/pipelines", POST, getV3Request(pipelineCreate), ptr);
            return entity.getBody().getContent();
        } catch (HttpClientErrorException e) {
            LOG.error("Can not create pipeline from template: {}, May be pipeline group {} dose not exist. Message: {}, Response: {}.",
                    templateName, groupName, e.getMessage(), e.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public Collection<PipelineGroup> getPipelineGroups() {
        try {
            ParameterizedTypeReference<Collection<PipelineGroup>> ptr = new ParameterizedTypeReference<Collection<PipelineGroup>>() {
            };
            ResponseEntity<Collection<PipelineGroup>> entity = this.restTemplate
                    .exchange(baseURI + "/api/config/pipeline_groups", GET, getStringRequest(), ptr);
            return entity.getBody();
        } catch (HttpClientErrorException e) {
            LOG.warn("Can not get pipeline groups, will return an empty list. Message: {}, Response: {}.",
                    e.getMessage(), e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }



    @Override
    public PipelineConfig getPipelineConfig(String name) {
        try {
            ParameterizedTypeReference<Resource<PipelineConfig>> ptr = new ParameterizedTypeReference<Resource<PipelineConfig>>() {
            };
            ResponseEntity<Resource<PipelineConfig>> entity = this.restTemplate
                    .exchange(baseURI + "/api/admin/pipelines/" + name, GET, getV3Request(), ptr);
            return entity.getBody().getContent();
        } catch (HttpClientErrorException e) {
            LOG.error("Can not get PipelineConfig of Pipeline: {}, Make sure it exists. Message: {}, Response: {}.",
                    name, e.getMessage(), e.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public Template createTemplateFromPipeline(String templateName, String pipelineName) {
        PipelineConfig config = this.getPipelineConfig(pipelineName);
        Template template = new Template()
                .setName(templateName)
                .setStages(config.getStages());
        try {
            ParameterizedTypeReference<Resource<Template>> ptr = new ParameterizedTypeReference<Resource<Template>>() {
            };
            ResponseEntity<Resource<Template>> entity = this.restTemplate
                    .exchange(baseURI + "/api/admin/templates", POST, getV2Request(template)
                            , ptr);
            return entity.getBody().getContent();
        } catch (HttpClientErrorException e) {
            LOG.error("Can not create template from pipeline {}. Message: {}, Response: {}.",
                    pipelineName, e.getMessage(), e.getResponseBodyAsString());
        }
        return null;
    }


    @Autowired
    public GoClientImpl setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        this.restTemplate.setRequestFactory(requestFactory);
        this.restTemplate.getMessageConverters()
                .stream()
                .filter(converter -> converter instanceof TypeConstrainedMappingJackson2HttpMessageConverter)
                .forEach(httpMessageConverter ->
                        ((TypeConstrainedMappingJackson2HttpMessageConverter) httpMessageConverter)
                                .setSupportedMediaTypes(Arrays.asList(
                                        MediaType.APPLICATION_JSON,
                                        MediaType.valueOf("application/vnd.go.cd.v1+json"),
                                        MediaType.valueOf("application/vnd.go.cd.v2+json"),
                                        MediaType.valueOf("application/vnd.go.cd.v3+json")
                                )));
        return this;
    }

    private HttpEntity<String> getStringRequest() {
        return new HttpEntity<>(buildHttpHeaders());
    }

    private HttpEntity<String> getStringRequestWithHeader(String header, String value) {
        HttpHeaders headers = buildHttpHeaders();
        headers.add(header, value);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<String> getV1Request() {
        return new HttpEntity<>(buildHttpHeaders("v1"));
    }

    private <T> HttpEntity<T> getV2Request(T req) {
        return new HttpEntity<>(req, buildHttpHeaders("v2"));
    }

    private HttpEntity<String> getV2Request() {
        return new HttpEntity<>(buildHttpHeaders("v2"));
    }

    private <T> HttpEntity<T> getV3Request(T req) {
        return new HttpEntity<>(req, buildHttpHeaders("v3"));
    }

    private HttpEntity<String> getV3Request() {
        return new HttpEntity<>(buildHttpHeaders("v3"));
    }

    private <T> HttpEntity<T> getV4Request(T req) {
        return new HttpEntity<>(req, buildHttpHeaders("v4"));
    }

    private HttpEntity<String> getV4Request() {
        return new HttpEntity<>(buildHttpHeaders("v4"));
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
        } else if ("v3".equals(apiVersion)) {
            headers.set("Accept", "application/vnd.go.cd.v3+json");
        } else if ("v4".equals(apiVersion)) {
            headers.set("Accept", "application/vnd.go.cd.v4+json");
        }else {
            throw new RuntimeException(apiVersion + " version dose not match any go cd API");
        }

        return headers;
    }

}
