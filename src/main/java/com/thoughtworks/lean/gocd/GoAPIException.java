package com.thoughtworks.lean.gocd;

public class GoAPIException extends Exception {
    GoAPIException(String content) {
        super(content);
    }
}
