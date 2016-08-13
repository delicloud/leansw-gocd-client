package com.thoughtworks.lean.gocd;

public class GOCDMaterial {

    private String description;
    private String fingerprint;
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GOCDMaterial{" +
                "description='" + description + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
