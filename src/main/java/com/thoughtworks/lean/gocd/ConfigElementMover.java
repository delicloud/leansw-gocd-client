package com.thoughtworks.lean.gocd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

public class ConfigElementMover {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(GoClient.class);

    private Document root;


    public ConfigElementMover(String cruiseXml) {
        this.root = Jsoup.parse(cruiseXml);
    }

    private Element pickOutGroup(String groupName) {
        Element originElement = getGroup(groupName);
        Element insertElement = originElement.clone();
        originElement.remove();
        return insertElement;
    }

    private Element pickOutPipeline(String pipelineName) {
        Element originElement = getPipeline(pipelineName);
        Element insertElement = originElement.clone();
        originElement.remove();
        return insertElement;
    }


    public void moveGroupToBottom(String groupName) {
        Element insertElement = pickOutGroup(groupName);
        getGroups().last().after(insertElement);
    }

    private Elements getGroups() {
        return root.select("cruise>pipelines");
    }

    private Elements getPipelines() {
        return root.select("cruise>pipelines>pipeline");
    }


    public void moveGroupToTop(String groupName) {
        Element insertElement = pickOutGroup(groupName);
        getGroups().first().before(insertElement);
    }

    public void moveGroupToBefore(String groupName, String beforeGroupName) {
        Element element = pickOutGroup(groupName);
        getGroup(beforeGroupName).before(element);
    }

    public void moveGroupToAfter(String groupName, String afterGroupName) {
        Element element = pickOutGroup(groupName);
        getGroup(afterGroupName).after(element);
    }

    public void moveGroupToIndex(String groupName, int index) {
        Element element = pickOutGroup(groupName);
        getGroups().get(index).before(element);
    }

    public void movePipelineToGroupTop(String pipelineName, String groupName) {
        Element pipeline = pickOutPipeline(pipelineName);
        Element group = getGroup(groupName);
        group.prependChild(pipeline);
    }
    public void movePipelineToGroupBottom(String pipelineName, String groupName) {
        Element pipeline = pickOutPipeline(pipelineName);
        Element group = getGroup(groupName);
        group.appendChild(pipeline);
    }

    public void movePipelineToGroupAndIndex(String pipelineName, String groupName ,int index){
        Element pipeline = pickOutPipeline(pipelineName);
        Element group = getGroup(groupName);
        group.select(">pipeline").get(index).before(pipeline);
    }

    private Element getGroup(String groupName) {
        return getGroups().stream().filter(element ->
                element.attr("group").equals(groupName)).findFirst().get();

    }

    private Element getPipeline(String pipelineName) {
        return getPipelines().stream().filter(element -> element.attr("name").equals(pipelineName)).findFirst().get();
    }

    public int getGroupIndex(String groupName) {
        Elements groups = getGroups();
        for (int i = 0; i < groups.size(); i++) {
            Element group = groups.get(i);
            if (group.attr("group").equals(groupName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("group name is not exist");
    }

    public int getGroupSize() {
        return getGroups().size();
    }

    public PipelineCoordinate getPipelineCoordinate(String groupName, String pipelineName) {
        return new PipelineCoordinate()
                .setGroupIndex(getGroupIndex(groupName))
                .setPipelineIndexInGroup(getPipelineIndexInGroup(getGroup(groupName), pipelineName));
    }

    public PipelineCoordinate getPipelineCoordinate(String pipelineName) {
        Elements groups = getGroups();
        for (int i = 0; i < groups.size(); i++) {
            Element group = groups.get(i);
            try {
                int indexInGroup = getPipelineIndexInGroup(group, pipelineName);
                return new PipelineCoordinate()
                        .setGroupIndex(i)
                        .setPipelineIndexInGroup(indexInGroup);
            } catch (IllegalArgumentException e) {
                //
            }
        }
        throw new IllegalArgumentException("group name is not exist");
    }


    private int getPipelineIndexInGroup(Element group, String pipelineName) {
        Elements pipelines = group.select(">pipeline");
        for (int i = 0; i < pipelines.size(); i++) {
            Element pipeline = pipelines.get(i);
            if (pipeline.attr("name").equals(pipelineName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("pipeline name is not exist in group '" + group.attr("group") + "'");
    }


}
