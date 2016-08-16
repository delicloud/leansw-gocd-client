package com.thoughtworks.lean.gocd.impl;

import com.google.common.io.Resources;
import com.thoughtworks.lean.gocd.dto.AgentInfo;
import com.thoughtworks.lean.gocd.dto.AgentsInfoResponse;
import com.thoughtworks.lean.gocd.dto.PipelineStatus;
import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import com.thoughtworks.lean.gocd.dto.history.Job;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistory;
import com.thoughtworks.lean.gocd.dto.history.PipelineHistoryResult;
import com.thoughtworks.lean.gocd.util.JSONUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static com.google.common.base.Charsets.UTF_8;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoClientImplTest {

    private GoClientImpl goClient;


    @Before
    public void setup() {
        String goHost = "http://gocd-server:8153/go/";
        goClient = new GoClientImpl(goHost, "admin", "badger");
    }

    @Test
    public void should_fetch_pipeline_config() {
        String configuration = goClient.getPipelineConfiguration("identity-server");
        //System.out.println(configuration);
    }

    @Test
    public void should_return_dash_board(){
        DashBoard dashBoard = goClient.getDashBoard();
        assertThat(dashBoard.getEmbedded().getPipelineGroups(), notNullValue());
    }

    @Test
    public void check_dashboard_domain() throws IOException {
        DashBoard dashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard.json"), UTF_8), DashBoard.class);
        assertThat(dashBoard.getEmbedded().getPipelineGroups(), notNullValue());

        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getEmbedded().getInstances().get(0).getEmbedded().getStages().get(0).getStatus(), notNullValue());
        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getEmbedded().getInstances().get(0).getEmbedded().getStages().get(0).getName(), notNullValue());
        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getPauseInfo(), notNullValue());
        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getEmbedded().getInstances().get(0).getScheduleAt(), notNullValue());
        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getEmbedded().getInstances().get(0).getTriggeredBy(), notNullValue());
        assertThat(dashBoard.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0).getEmbedded().getInstances().get(0).getLabel(), notNullValue());
    }

    @Test
    public void should_fetch_pipeline_instance() {
        PipelineHistory pipelineHistory = goClient.getPipelineInstance("cd-metrics-ui", 1);
        //System.out.println(configuration.toString());
        assertNotNull(pipelineHistory);
        assertTrue(pipelineHistory.getDuration() > 0);
        assertNotNull(pipelineHistory.getResult());
    }


    @Ignore
    @Test
    public void should_fetch_pipeline_job_log() {
        String log = goClient.fetchCruiseLog("cd-metrics-ui", 410, "check", 1, "eslint");
        //System.out.println(log);
    }

    @Ignore
    @Test
    public void should_fetch_pipeline_job_properites() {

        Map properties = goClient.fetchJobProperties("cd-metrics-ui", 410, "check", 1, "eslint");
        //System.out.println(properties);
        assertEquals(Integer.parseInt(properties.get("cruise_pipeline_counter").toString()), 410);
    }

    @Test
    public void should_get_go_cd_agent_info() {
        AgentInfo agentInfo = goClient.getAgent("fcbd4ecc-9952-4e06-a9c4-ad2f1f97f618");
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
    public void should_get_pipeline_status() {
        PipelineStatus pipelineStatus = goClient.fetchPipelineStatus("cd-metrics-ui");
        assertNotNull(pipelineStatus);
    }


    @Ignore
    @Test
    public void should_trigger_pipeline_test_pipeline_1() {
        goClient.schedule("test-pipeline-1");
    }

    @Ignore
    @Test
    public void should_pause_pipeline_test_pipeline_1() {
        goClient.pause("test-pipeline-1");
    }

    @Ignore
    @Test
    public void should_unpause_pipeline_test_pipeline_1() {
        goClient.resume("test-pipeline-1");
    }


}