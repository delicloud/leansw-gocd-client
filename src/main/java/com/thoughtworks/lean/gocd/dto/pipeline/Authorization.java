package com.thoughtworks.lean.gocd.dto.pipeline;

import java.util.List;

public class Authorization {
    private List<String> users;
    private List<String> roles;

    public Authorization() {
    }

    public List<String> getUsers() {
        return users;
    }

    public Authorization setUsers(List<String> users) {
        this.users = users;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Authorization setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
