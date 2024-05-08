package com.helpline.helplineapi.data.contract.job.list;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.job.JobContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ListJobResponse extends BaseResponse {

    private List<JobContract> jobs;

    private int totalPages;

    private long totalElements;
}