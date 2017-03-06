package com.thoughtworks.lean.gocd.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.lean.gocd.config.ApplicationConfig;
import com.thoughtworks.lean.gocd.config.TestApplicationConfig;
import com.thoughtworks.lean.gocd.dto.AgentInfo;
import com.thoughtworks.lean.gocd.dto.AgentsInfoResponse;
import com.thoughtworks.lean.gocd.dto.PipelineStatus;
import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import com.thoughtworks.lean.gocd.dto.history.Job;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistory;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistoryResult;
import com.thoughtworks.lean.gocd.dto.pipeline.PipelineConfig;
import com.thoughtworks.lean.gocd.dto.pipeline.PipelineGroup;
import com.thoughtworks.lean.gocd.dto.pipeline.Template;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
// ApplicationContext will be loaded from the static inner ContextConfiguration class
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ApplicationConfig.class, TestApplicationConfig.class})
public class GoClientImplTest {

    @Autowired
    private GoClientImpl goClient;

    public RestTemplate getRestTemplateWithHalMessageConverter() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> existingConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> newConverters = new ArrayList<>();
        newConverters.add(getHalMessageConverter());
        newConverters.addAll(existingConverters);
        restTemplate.setMessageConverters(newConverters);
        return restTemplate;
    }

    private HttpMessageConverter getHalMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingJackson2HttpMessageConverter halConverter = new TypeConstrainedMappingJackson2HttpMessageConverter(ResourceSupport.class);
        halConverter.setObjectMapper(objectMapper);
        return halConverter;
    }

    @Before
    public void setup() {
        goClient.setRestTemplate(getRestTemplateWithHalMessageConverter());
    }

    @Test
    public void should_return_dash_board() {
        DashBoard dashBoard = goClient.getDashBoard();
        assertThat(dashBoard.getPipelineGroups(), notNullValue());
    }


    @Test
    public void should_fetch_pipeline_instance() {
        PipelineHistory pipelineHistory = goClient.getPipelineInstance("cd-metrics-ui", 1);
        assertNotNull(pipelineHistory);
        assertTrue(pipelineHistory.getDuration() > 0);
        assertNotNull(pipelineHistory.getResult());
    }


    @Test
    public void should_fetch_pipeline_instance_incomplete() {
        PipelineHistory pipelineHistory = goClient.getPipelineInstance("cd-metrics-ui", 1, false);
        assertNotNull(pipelineHistory);
        assertTrue(pipelineHistory.getDuration() == 0);
        assertNull(pipelineHistory.getResult());
    }

    @Test
    public void should_fetch_pipeline_job_log() {
        String log = goClient.fetchCruiseLog("cd-metrics-ui", 655, "check", 1, "eslint_kamar_test");
    }

    @Test
    public void should_fetch_pipeline_job_properites() {

        Map properties = goClient.fetchJobProperties("cd-metrics-ui", 410, "check", 1, "eslint");
        assertEquals(Integer.parseInt(properties.get("cruise_pipeline_counter").toString()), 410);
    }

    @Test
    @Ignore
    public void should_get_go_cd_agent_info() {
        AgentInfo agentInfo = goClient.getAgent("5362b3ab-1a25-4c55-af36-8db31cbaffab");
        assertEquals("4cb11329123d", agentInfo.getHostname());
    }

    @Test
    @Ignore
    public void should_get_go_cd_agent_info2() {
        AgentInfo agentInfo = goClient.getAgent("d75d4892-4af1-4bcb-9cf8-256b24cce6bf");
        assertEquals("iZ28h2t7rzrZ", agentInfo.getHostname());
    }

    @Test
    @Ignore
    public void should_start_manual_stage() {
        goClient.manualRunPipelineStage("test-pipeline-1", 99, "testManual");
    }

    @Test
    public void should_get_properties() {
        Map<String, String> properties = goClient.fetchJobProperties("cd-metrics-ui", 1, "build_image", 1, "containeized");
        assertThat(properties.get("cruise_agent"), is(notNullValue()));
    }

    @Test
    public void should_featch_all_agents() {
        AgentsInfoResponse agentsInfoResponse = goClient.fetchAllAgents();
        assertTrue(agentsInfoResponse.getEmbedded().getAgents().size() > 0);
    }

    @Test
    public void should_get_correct_pipeline_history_result() {

        PipelineHistoryResult result = goClient.getPipelineHistory("cd-metrics-ui", 0);
        Job firstJob = result.getPipelines().get(0).getStages().get(0).getJobs().get(0);
        assertNotNull(firstJob);
        assertTrue(firstJob.getProperties().size() > 0);

        PipelineHistory firstPipeline = result.getPipelines().get(0);
        assertNotNull(firstPipeline);
        assertTrue(firstPipeline.getDuration() > 0);
        assertNotNull(firstPipeline.getResult());
    }


    @Test
    public void should_get_correct_pipeline_history_result_incomplete() {

        PipelineHistoryResult result = goClient.getPipelineHistory("cd-metrics-ui", 0, false);
        PipelineHistory firstPipeline = result.getPipelines().get(0);
        assertNotNull(firstPipeline);
        assertNull(firstPipeline.getResult());
        assertTrue(firstPipeline.getDuration() == 0);
    }

    @Test
    public void should_get_pipeline_status() {
        PipelineStatus pipelineStatus = goClient.fetchPipelineStatus("cd-metrics-ui");
        assertNotNull(pipelineStatus);
    }


    @Test
    @Ignore
    public void should_trigger_pipeline_test_pipeline_1() {
        goClient.schedule("test-pipeline-1");
    }

    @Test
    public void should_pause_pipeline_test_pipeline_1() {
        goClient.pause("test-pipeline-1");
    }

    @Test
    public void should_unpause_pipeline_test_pipeline_1() {
        goClient.resume("test-pipeline-1");
    }

    @Test
    public void should_get_all_templates() throws Exception {
        Collection<Template> templateLsit = goClient.getAllTemplates();
        assertTrue(templateLsit.size() > 0);
    }

    @Test
    public void should_get_template() throws Exception {
        Template template = goClient.getTemplate("test-template1");
        assertNotNull(template);
        assertTrue(template.getStages().size() > 0);
    }

    @Ignore
    @Test
    public void should_create_new_pipeline_using_template() throws Exception {
        Template template = goClient.getTemplate("test-template1");
        PipelineConfig config = goClient.createPipelineFromTemplate("new-test-template1", "test", "test-template1", "git@github.com:tw-leansw/leansw-gocd-agent-docker.git", "other-branch");
        assertEquals(config.getStages().size(), template.getStages().size());
    }

    @Test
    public void should_get_all_pipeline_groups() throws Exception {
        Collection<PipelineGroup> groups = goClient.getPipelineGroups();
        assertTrue(groups.size() > 0);

    }

    @Test
    public void should_get_pipeline_config_by_name() throws Exception {
        PipelineConfig config = goClient.getPipelineConfig("test-pipeline-2");
        assertEquals("defaultStage", config.getStages().get(0).getName());
    }

    @Test
    @Ignore
    public void should_create_template_from_pipeline() throws Exception {
        Template template = goClient.createTemplateFromPipeline("new-template", "test-pipeline-2");
        PipelineConfig config = goClient.getPipelineConfig("test-pipeline-2");
        assertEquals(template.getStages().get(0).getName(), config.getStages().get(0).getName());
    }

    @Ignore
    @Test
    public void should_get_all_pipeline_status() throws Exception {
        DashBoard dashBoard = goClient.getDashBoard();
        dashBoard.getPipelineGroups()
                .forEach(pipelineGroup -> pipelineGroup.getPipelines()
                        .forEach(pipeline -> System.out.println(pipeline.getName() + " | " + pipeline.getCurrentStatus())));

    }
}
