package com.thoughtworks.lean.gocd.dto.pipeline;

public class Configuration {
    String key;
    String value;

    public Configuration(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Configuration() {
    }

    public String getKey() {
        return key;
    }

    public Configuration setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Configuration setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
