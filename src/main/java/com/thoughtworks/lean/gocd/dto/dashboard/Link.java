package com.thoughtworks.lean.gocd.dto.dashboard;

public class Link {
    private String href;
    public String getHref() {
        return href;
    }

    public Link setHref(String href) {
        this.href = href;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return href != null ? href.equals(link.href) : link.href == null;

    }

    @Override
    public int hashCode() {
        return href != null ? href.hashCode() : 0;
    }
}
