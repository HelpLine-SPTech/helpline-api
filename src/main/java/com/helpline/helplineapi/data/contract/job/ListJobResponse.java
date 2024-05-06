package com.helpline.helplineapi.data.contract.job;

import com.helpline.helplineapi.data.contract.BaseResponse;

import java.util.List;

public class ListJobResponse extends BaseResponse {
    private List<JobContract> jobs;
    private int totalPages;
    private long totalElements;

    public List<JobContract> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobContract> jobs) {
        this.jobs = jobs;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}