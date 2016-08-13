package com.thoughtworks.lean.gocd;

import com.thoughtworks.lean.gocd.dto.pipeline.Material;
import com.thoughtworks.lean.gocd.dto.pipeline.PipelineConfig;
import com.thoughtworks.lean.gocd.dto.pipeline.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CruiseXmlParser {

    private Document root;

    public CruiseXmlParser(String cruiseXml) {
        this.root = Jsoup.parse(cruiseXml);
    }

    public List<String> getPipelineGroups() {
        Elements pipelineGroups = root.select("pipelines");
        return pipelineGroups.stream().map(toPipelineGroupNames()).collect(Collectors.toList());
    }


    public List<PipelineConfig> getPipelines(final String group) {
        Element groupElm = root.select(String.format("pipelines[group=%s]", group)).first();
        Elements pipelineElms = groupElm.select("pipeline");
        return pipelineElms.stream().map(toPipelineConfig(group)).filter(isNotEmptyPipeline()).collect(Collectors.toList());
    }

    private Predicate<PipelineConfig> isNotEmptyPipeline() {
        return pipelineConfig -> pipelineConfig.getName() != null && !pipelineConfig.getName().equals("");
    }

    private Function<Element, String> toPipelineGroupNames() {
        return element -> element.attr("group");
    }

    private Function<Element, PipelineConfig> toPipelineConfig(final String group) {
        return element -> {
            String name = element.attr("name");
            PipelineConfig pipelineConfig = new PipelineConfig();
            pipelineConfig.setName(name);
            pipelineConfig.setGroupName(group);
            pipelineConfig.setCreateDate(new Date());
            Elements materials = element.getElementsByTag("materials");
            if (!materials.isEmpty()) {
                pipelineConfig.setMaterials(toMaterials(materials.first(), name));
            }
            pipelineConfig.setStages(toStages(element.getElementsByTag("stage"), name));
            return pipelineConfig;
        };
    }

    private List<Stage> toStages(Elements stageElms, final String pipelineName) {
        return stageElms.stream().map(element -> {
            Stage stage = new Stage();

            stage.setName(element.attr("name"));
            stage.setPipelineName(pipelineName);
            stage.setId((pipelineName + element.attr("name")).hashCode());
            return stage;
        }).collect(Collectors.toList());

    }

    private List<Material> toMaterials(Element materialsElm, final String pipelineName) {
        return materialsElm.children().stream().map(element -> {
            Material material = new Material();
            material.setId((pipelineName + element.tagName() + element.toString()).hashCode());
            material.setDescription(element.toString());
            material.setType(element.tagName());
            material.setPipelineName(pipelineName);
            return material;
        }).collect(Collectors.toList());
    }
}
