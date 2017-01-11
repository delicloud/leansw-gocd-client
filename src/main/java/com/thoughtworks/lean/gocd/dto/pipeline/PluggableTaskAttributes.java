package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class PluggableTaskAttributes implements TaskAttributes {

    @JsonProperty("run_if")
    List<String> runIf;

    @JsonProperty("plugin_configuration")
    PluginConfiguration pluginConfiguration;

    List<Configuration> configuration;

    @JsonProperty("on_cancel")
    Task onCancel;

    public PluggableTaskAttributes() {
    }

    public List<String> getRunIf() {
        return runIf;
    }

    public PluggableTaskAttributes setRunIf(List<String> runIf) {
        this.runIf = runIf;
        return this;
    }

    public PluginConfiguration getPluginConfiguration() {
        return pluginConfiguration;
    }

    public PluggableTaskAttributes setPluginConfiguration(PluginConfiguration pluginConfiguration) {
        this.pluginConfiguration = pluginConfiguration;
        return this;
    }

    public List<Configuration> getConfiguration() {
        return configuration;
    }

    public PluggableTaskAttributes setConfiguration(List<Configuration> configuration) {
        this.configuration = configuration;
        return this;
    }

    public Task getOnCancel() {
        return onCancel;
    }

    public PluggableTaskAttributes setOnCancel(Task onCancel) {
        this.onCancel = onCancel;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("runIf", runIf)
                .append("pluginConfiguration", pluginConfiguration)
                .append("configuration", configuration)
                .append("onCancel", onCancel)
                .toString();
    }
}
