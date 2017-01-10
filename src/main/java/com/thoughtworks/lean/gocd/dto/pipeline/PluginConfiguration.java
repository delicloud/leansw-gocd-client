package com.thoughtworks.lean.gocd.dto.pipeline;

public class PluginConfiguration {
    private String id;
    private String version;

    public PluginConfiguration(String id, String version) {
        this.id = id;
        this.version = version;
    }

    public PluginConfiguration() {
    }

    public String getId() {
        return id;
    }

    public PluginConfiguration setId(String id) {
        this.id = id;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public PluginConfiguration setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "PluginConfiguration{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
