package com.thoughtworks.lean.gocd.dto.pipeline;

public class ScmMaterialAttributes implements MaterialAttributes {
    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "ScmMaterialAttributes{" +
                "ref='" + ref + '\'' +
                '}';
    }
}
