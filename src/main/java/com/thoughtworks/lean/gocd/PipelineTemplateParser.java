package com.thoughtworks.lean.gocd;

import com.thoughtworks.lean.gocd.dto.pipeline.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PipelineTemplateParser {

    private Document root;

    public PipelineTemplateParser(String templateXml) {
        this.root = Jsoup.parse(templateXml);
    }

    public List<PipelineTemplate> getPipelines() {
        Elements pipelines = this.root.select("pipelines > pipeline");
        return pipelines.stream().map(element -> toPipelineTemplate(element)).collect(Collectors.toList());
    }

    private PipelineTemplate toPipelineTemplate(Element element) {
        PipelineTemplate template = new PipelineTemplate();
        template.setPipeline(getPipelineConfig(element));
        return template;
    }

    private PipelineConfig getPipelineConfig(Element element) {
        PipelineConfig pipelineConfig = new PipelineConfig();
        pipelineConfig.setName(element.attr("name"));
        Element timerEle = element.getElementsByTag("timer").first();
        pipelineConfig.setTimer(getTimer(timerEle));

        pipelineConfig.setMaterials(toMaterials(element.getElementsByTag("materials").first().children()));
        pipelineConfig.setStages(toStages(element.getElementsByTag("stage")));
        return pipelineConfig;
    }

    private List<Stage> toStages(Elements stages) {
        return stages.stream().map(element -> {
            Stage stage = new Stage();
            stage.setJobs(toJobs(element.getElementsByTag("job")));
            stage.setName(element.attr("name"));
            stage.setFetch_materials(Boolean.parseBoolean(element.attr("fetchmaterials")));
            return stage;
        }).collect(Collectors.toList());
    }

    private List<Job> toJobs(Elements jobs) {
        return jobs.stream().map(jobEle -> {
            Job job = new Job();
            job.setName(jobEle.attr("name"));
            job.setTasks(toTasks(jobEle.getElementsByTag("tasks").first().children()));
            job.setResources(toTaskResources(jobEle.getElementsByTag("resource")));
            return job;
        }).collect(Collectors.toList());
    }

    private List<String> toTaskResources(Elements resources) {
        return toElementText(resources).collect(Collectors.toList());
    }

    private List<String> toTaskArgs(Elements argsEle) {
        return toElementText(argsEle).collect(Collectors.toList());
    }

    private List<Task> toTasks(Elements tasks) {
        return tasks.stream().map(taskEle -> {
            Task task = new Task();
            task.setType(taskEle.tagName());
            task.setAttributes(toTaskAttribute(taskEle));
            return task;
        }).collect(Collectors.toList());
    }

    private TaskAttributes toTaskAttribute(Element taskEle) {
        // TODO: other task attribute
        if("exec".equals(taskEle.tagName())){
            ExecTaskAttributes execTaskAttributes = new ExecTaskAttributes();
            execTaskAttributes.setCommand(taskEle.attr("command"));
            execTaskAttributes.setRun_if(toTaskRunIfAttr(taskEle.getElementsByTag("runif")));
            execTaskAttributes.setArguments(toTaskArgs(taskEle.getElementsByTag("arg")));
            return execTaskAttributes;
        }
        return null;
    }

    private List<String> toTaskRunIfAttr(Elements runifEle) {
        return runifEle.stream().map(element -> element.attr("status")).collect(Collectors.toList());
    }

    private List<Material> toMaterials(Elements materials) {
        return materials.stream().map(element -> {
            Material pipelineMaterial2 = new Material();
            if ("pipeline".equals(element.tagName())) {
                pipelineMaterial2.setType("dependency");
            } else {
                pipelineMaterial2.setType(element.tagName());
            }

            pipelineMaterial2.setAttributes(toAttribute(element));
            return pipelineMaterial2;
        }).collect(Collectors.toList());
    }

    private Attributes toAttribute(Element element) {
        // TODO: (other else)
        String tag = element.tagName();
        if ("git".equals(tag)) {
            GitMaterialAttributes gitMaterialAttributes = new GitMaterialAttributes();
            gitMaterialAttributes.setUrl(element.attr("url"));
            return gitMaterialAttributes;
        } else if ("scm".equals(tag)) {
            ScmMaterialAttributes scmMaterialAttributes = new ScmMaterialAttributes();
            scmMaterialAttributes.setRef(element.attr("ref"));
            return scmMaterialAttributes;
        } else if("pipeline".equals(tag)){
            DependencyMaterialAttributes dependencyMaterialAttributes = new DependencyMaterialAttributes();

            dependencyMaterialAttributes.setPipeline(element.attr("pipelineName"));
            dependencyMaterialAttributes.setStage(element.attr("stageName"));
            return dependencyMaterialAttributes;
        }
        return null;
    }

    private Timer getTimer(Element timerEle) {
        if (null == timerEle) {
            return null;
        }
        Timer timer = new Timer();
        timer.setOnly_on_changes(Boolean.parseBoolean(timerEle.attr("onlyonchanges")));
        timer.setSpec(timerEle.text());
        return timer;
    }


    private Stream<String> toElementText(Elements argsEle) {
        return argsEle.stream().map(Element::text);
    }
}
