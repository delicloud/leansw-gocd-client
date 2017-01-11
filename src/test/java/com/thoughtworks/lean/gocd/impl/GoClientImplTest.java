package com.thoughtworks.lean.gocd.impl;

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
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class GoClientImplTest {

    private GoClientImpl goClient;

    @Before
    public void setup() {
        String goHost = "http://gocd-server:8153/go/";
        goClient = new GoClientImpl(new RestTemplate(), goHost, "admin", "badger");
    }

    @Test
    public void should_return_dash_board() {
        DashBoard dashBoard = goClient.getDashBoard();
        assertThat(dashBoard.getEmbedded().getPipelineGroups(), notNullValue());
    }


    @Test
    public void should_fetch_pipeline_instance() {
        PipelineHistory pipelineHistory = goClient.getPipelineInstance("cd-metrics-ui", 1);
        //System.out.println(configuration.toString());
        assertNotNull(pipelineHistory);
        assertTrue(pipelineHistory.getDuration() > 0);
        assertNotNull(pipelineHistory.getResult());
    }


    @Test
    public void should_fetch_pipeline_instance_incomplete() {
        PipelineHistory pipelineHistory = goClient.getPipelineInstance("cd-metrics-ui", 1, false);
        //System.out.println(configuration.toString());
        assertNotNull(pipelineHistory);
        assertTrue(pipelineHistory.getDuration() == 0);
        assertNull(pipelineHistory.getResult());
    }

    @Test
    public void should_fetch_pipeline_job_log() {
        String log = goClient.fetchCruiseLog("cd-metrics-ui", 628, "check", 1, "eslint_kamar_test");
        //System.out.println(log);
    }

    @Test
    public void should_fetch_pipeline_job_properites() {

        Map properties = goClient.fetchJobProperties("cd-metrics-ui", 410, "check", 1, "eslint");
        //System.out.println(properties);
        assertEquals(Integer.parseInt(properties.get("cruise_pipeline_counter").toString()), 410);
    }

    @Test
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
        templateLsit.stream().map(Template::getName).forEach(System.out::println);
    }

    @Test
    public void should_get_template() throws Exception {
        Template template = goClient.getTemplate("test-template12");
        assertNotNull(template);
    }

    @Test
    @Ignore
    public void should_create_new_pipeline_using_template() throws Exception {
        Template template = goClient.getTemplate("test-template1");
        goClient.createPipelineFromTemplate("new-test-template1","test" , "test-template1");
    }

    @Test
    public void should_get_all_pipeline_groups() throws Exception {
        Collection<PipelineGroup> groups = goClient.getPipelineGroups();
        assertTrue(groups.size() > 0);

    }

    @Test
    public void should_get_pipeline_config_by_name() throws Exception {
        PipelineConfig config = goClient.getPipelineConfig("test-pipeline-21");
        assertEquals("defaultStage",config.getStages().get(0).getName());
    }

    @Test
    @Ignore
    public void should_create_template_from_pipeline() throws Exception {
        Template template = goClient.createTemplateFromPipeline("new-template", "test-pipeline-2");
        PipelineConfig config = goClient.getPipelineConfig("test-pipeline-2");
        assertEquals(template.getStages().get(0).getName(),config.getStages().get(0).getName());
    }
}
