package com.thoughtworks.lean.gocd.dto.dashboard;

/**
 * Created by yongliuli on 8/16/16.
 */
public class Stage {
    String name;
    String status;

    public String getName() {
        return name;
    }

    public Stage setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Stage setStatus(String status) {
        this.status = status;
        return this;
    }
}
