package com.thoughtworks.lean.gocd;

public class GOCDStage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GOCDStage{" +
                "name='" + name + '\'' +
                '}';
    }
}
