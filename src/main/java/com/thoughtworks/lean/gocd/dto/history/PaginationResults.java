package com.thoughtworks.lean.gocd.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qmxie on 4/26/16.
 */
public class PaginationResults<T> {

    private List<T> results = new ArrayList<>();
    private Pagination pagination;

    public PaginationResults() {
        // plain constructor
    }

    public PaginationResults(Pagination pagination, List<T> results) {
        this.pagination = pagination;
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
