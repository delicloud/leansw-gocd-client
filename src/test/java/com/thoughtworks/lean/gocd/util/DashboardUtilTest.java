package com.thoughtworks.lean.gocd.util;

import com.google.common.io.Resources;
import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static com.google.common.base.Charsets.UTF_8;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yongliuli on 11/29/16.
 */
public class DashboardUtilTest {


    @Test
    public void check_pipeline_names() throws IOException {
        DashBoard dashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_2.json"), UTF_8), DashBoard.class);
        assertThat(dashBoard.getPipelineGroups(), notNullValue());
        assertThat(DashboardUtil.getPipelineNames(dashBoard).size(), equalTo(3));
        assertTrue(DashboardUtil.getPipelineNames(dashBoard).contains("test-pipeline-3"));
    }

    @Test
    public void check_pipelineGroup_names() throws IOException {
        //given
        DashBoard dashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_2.json"), UTF_8), DashBoard.class);
        assertThat(dashBoard.getPipelineGroups(), notNullValue());
        //when then
        assertThat(DashboardUtil.getPipelineGroupNames(dashBoard).size(), equalTo(1));
        assertTrue(DashboardUtil.getPipelineGroupNames(dashBoard).contains("test"));
    }


    @Test
    public void check_dashboard_need_update_mock_instance() throws IOException {
        //given
        DashBoard oldDashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_2.json"), UTF_8), DashBoard.class);
        //change pipelineInstanceUrl add mock_data
        DashBoard newDashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_3.json"), UTF_8), DashBoard.class);
        //when
        Set<String> pipelinesNeedUpdate = DashboardUtil.getPipelineNeedUpdate(oldDashBoard, newDashBoard);

        //then
        assertTrue(pipelinesNeedUpdate.contains("test-pipeline-3"));
    }

    @Test
    public void check_dashboard_need_update_mock_stage() throws IOException {
        //given
        DashBoard oldDashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_2.json"), UTF_8), DashBoard.class);
        //change pipelineInstanceUrl add mock_data
        DashBoard newDashBoard = JSONUtil.parseJSON(Resources.toString(this.getClass().getResource("/test_dashboard_4.json"), UTF_8), DashBoard.class);
        //when
        Set<String> pipelinesNeedUpdate = DashboardUtil.getPipelineNeedUpdate(oldDashBoard, newDashBoard);

        //then
        assertTrue(pipelinesNeedUpdate.contains("test-pipeline-3"));
    }
}
