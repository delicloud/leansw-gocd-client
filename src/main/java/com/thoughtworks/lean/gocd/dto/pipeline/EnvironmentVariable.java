package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EnvironmentVariable {
    private String name;

    private String value;

    @JsonProperty("encrypted_value")
    private String encryptedValue;

    private Boolean secure;

    public EnvironmentVariable() {
    }

    public String getName() {
        return name;
    }

    public EnvironmentVariable setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public EnvironmentVariable setValue(String value) {
        this.value = value;
        return this;
    }

    public String getEncryptedValue() {
        return encryptedValue;
    }

    public EnvironmentVariable setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
        return this;
    }

    public Boolean getSecure() {
        return secure;
    }

    public EnvironmentVariable setSecure(Boolean secure) {
        this.secure = secure;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("value", value)
                .append("encryptedValue", encryptedValue)
                .append("secure", secure)
                .toString();
    }
}
