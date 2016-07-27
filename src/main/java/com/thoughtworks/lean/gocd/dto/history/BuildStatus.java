package com.thoughtworks.lean.gocd.dto.history;


import com.google.common.collect.ImmutableList;

public enum BuildStatus {
    Unknown,
    Building,
    Scheduled,
    Passed,
    Failed,
    Cancelled;

    public static boolean isNotCompleted(String state) {
        return ImmutableList.of(Scheduled.name(), Building.name()).contains(state);
    }
}
