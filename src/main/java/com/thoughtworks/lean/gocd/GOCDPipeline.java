package com.thoughtworks.lean.gocd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class GOCDPipeline {

    private String name;

    private List<GOCDStage> stages;

    private List<GOCDMaterial> materials;

    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GOCDStage> getStages() {
        return stages;
    }

    public void setStages(List<GOCDStage> stages) {
        this.stages = stages;
    }

    public List<GOCDMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<GOCDMaterial> materials) {
        this.materials = materials;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "GOCDPipeline{" +
                "name='" + name + '\'' +
                ", stages=" + stages +
                ", materials=" + materials +
                ", label='" + label + '\'' +
                '}';
    }
}
