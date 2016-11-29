package com.thoughtworks.lean.gocd.util;

import com.thoughtworks.lean.gocd.dto.dashboard.DashBoard;
import com.thoughtworks.lean.gocd.dto.dashboard.Pipeline;
import com.thoughtworks.lean.gocd.dto.dashboard.PipelineGroup;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashboardUtil {

    public static Set<String> getPipelineNames(DashBoard dashBoard) {
        return toPipelineStream(dashBoard).map(Pipeline::getName).collect(Collectors.toSet());
    }

    public static Set<String> getPipelineGroupNames(DashBoard dashBoard) {
        return dashBoard.getEmbedded().getPipelineGroups().stream()
                .map(PipelineGroup::getName).collect(Collectors.toSet());
    }

    public static Stream<Pipeline> toPipelineStream(DashBoard dashBoard) {
        return dashBoard.getEmbedded().getPipelineGroups().stream()
                .flatMap(pipelineGroup -> pipelineGroup.getEmbedded().getPipelines().stream());
    }


    public static Set<String> getPipelineNeedUpdate(DashBoard oldDashboard, DashBoard newDashBoard) {
        Map<String, Pipeline> oldPipelineMap = toPipelineStream(oldDashboard).collect(Collectors.toMap(Pipeline::getName, e -> e));
        return toPipelineStream(newDashBoard)
                .filter(pipeline -> !pipeline.equals(oldPipelineMap.get(pipeline.getName())))
                .map(Pipeline::getName).collect(Collectors.toSet());
    }


}