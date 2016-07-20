package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {

    private int offset = 0;
    private int total;
    @JsonProperty("page_size")
    private int pageSize;

    public Pagination() {
        // empty constructor
    }

    public Pagination(int offset, int pageSize, int total) {
        this.offset = offset;
        this.total = total;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean hasNextPage() {
        return nextPage() < total;
    }

    public int nextPage() {
        return offset + pageSize;
    }

    public int getTotalPage() {
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    public int getCurrentPage() {
        return offset / pageSize;
    }
}
