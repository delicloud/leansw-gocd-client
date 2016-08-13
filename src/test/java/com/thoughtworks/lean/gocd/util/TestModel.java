package com.thoughtworks.lean.gocd.util;

public class TestModel {
    boolean foo;
    String name;
    public TestModel(boolean foo) {
        this.foo = foo;
    }

    public boolean isFoo() {
        return foo;
    }

    public void setFoo(boolean foo) {
        this.foo = foo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
