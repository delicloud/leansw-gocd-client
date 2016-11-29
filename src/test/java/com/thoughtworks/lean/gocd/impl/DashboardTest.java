package com.thoughtworks.lean.gocd.impl;

import com.google.common.io.Resources;
import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import com.thoughtworks.lean.gocd.dto.dashboard.Pipeline;
import com.thoughtworks.lean.gocd.dto.dashboard.PipelineInstance;
import com.thoughtworks.lean.gocd.util.JSONUtil;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yongliuli on 11/29/16.
 */
public class DashboardTest {

    @Test
    public void check_dashboard_domain_value_exist() throws IOException {
        DashBoard dashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard.json"), UTF_8), DashBoard.class);
        assertThat(dashBoard.getEmbedded().getPipelineGroups(), notNullValue());
        assertThat(getDashboardFirstPipelineInstance(dashBoard).getEmbedded().getStages().get(0).getStatus(), notNullValue());
        assertThat(getDashboardFirstPipelineInstance(dashBoard).getEmbedded().getStages().get(0).getName(), notNullValue());
        assertThat(getDashboardFirstPipeline(dashBoard).getPauseInfo(), notNullValue());
        assertThat(getDashboardFirstPipelineInstance(dashBoard).getScheduleAt(), notNullValue());
        assertThat(getDashboardFirstPipelineInstance(dashBoard).getTriggeredBy(), notNullValue());
        assertThat(getDashboardFirstPipelineInstance(dashBoard).getLabel(), notNullValue());


    }

    @Test
    public void should_pipeline_changed_be_checked() throws IOException {

        DashBoard dashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard.json"), UTF_8), DashBoard.class);
        DashBoard dashBoard1 = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard.json"), UTF_8), DashBoard.class);

        assertEquals(dashBoard, dashBoard1);
        getDashboardFirstPipelineInstance(dashBoard1).getEmbedded().getStages().get(0).getLinks().get("self").setHref("1");
        assertNotEquals(dashBoard, dashBoard1);
        assertNotEquals(getDashboardFirstPipeline(dashBoard), getDashboardFirstPipeline(dashBoard1));

    }




    private PipelineInstance getDashboardFirstPipelineInstance(DashBoard dashBoard) {
        return getDashboardFirstPipeline(dashBoard).getEmbedded().getInstances().get(0);
    }

    private Pipeline getDashboardFirstPipeline(DashBoard dashBoard1) {
        return dashBoard1.getEmbedded().getPipelineGroups().get(0).getEmbedded().getPipelines().get(0);
    }

}
