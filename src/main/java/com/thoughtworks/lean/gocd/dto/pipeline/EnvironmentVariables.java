package com.thoughtworks.lean.gocd.dto.pipeline;

public class EnvironmentVariables {
    String name;
    String value;
    String encrypted_value;
    boolean secure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEncrypted_value() {
        return encrypted_value;
    }

    public void setEncrypted_value(String encrypted_value) {
        this.encrypted_value = encrypted_value;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
