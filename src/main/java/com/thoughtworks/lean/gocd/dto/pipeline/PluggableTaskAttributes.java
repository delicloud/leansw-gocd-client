package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class PluggableTaskAttributes implements TaskAttributes {
    List<String> run_if;
    PluginConfiguration plugin_configuration;
    List<Configuration> configuration;
    Task on_cancel;

    public PluggableTaskAttributes() {
    }

    public List<String> getRun_if() {
        return run_if;
    }

    public PluggableTaskAttributes setRun_if(List<String> run_if) {
        this.run_if = run_if;
        return this;
    }

    public PluginConfiguration getPlugin_configuration() {
        return plugin_configuration;
    }

    public PluggableTaskAttributes setPlugin_configuration(PluginConfiguration plugin_configuration) {
        this.plugin_configuration = plugin_configuration;
        return this;
    }

    public List<Configuration> getConfiguration() {
        return configuration;
    }

    public PluggableTaskAttributes setConfiguration(List<Configuration> configuration) {
        this.configuration = configuration;
        return this;
    }

    public Task getOn_cancel() {
        return on_cancel;
    }

    public PluggableTaskAttributes setOn_cancel(Task on_cancel) {
        this.on_cancel = on_cancel;
        return this;
    }

    @Override
    public String toString() {
        return "PluggableTaskAttributes{" +
                "run_if=" + run_if +
                ", plugin_configuration=" + plugin_configuration +
                ", configuration=" + configuration +
                ", on_cancel=" + on_cancel +
                '}';
    }
}
