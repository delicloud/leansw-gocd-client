package com.thoughtworks.lean.gocd.dto.history;

public class Material {

    private String description;
    private String fingerprint;
    private String type;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Material{" +
                "description='" + description + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
