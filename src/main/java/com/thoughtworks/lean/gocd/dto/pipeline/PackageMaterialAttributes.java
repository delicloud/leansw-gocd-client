package com.thoughtworks.lean.gocd.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class PackageMaterialAttributes implements MaterialAttributes{

    private String ref;

    public PackageMaterialAttributes() {
    }

    public String getRef() {
        return ref;
    }

    public PackageMaterialAttributes setRef(String ref) {
        this.ref = ref;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ref", ref)
                .toString();
    }
}
