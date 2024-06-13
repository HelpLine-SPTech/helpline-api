package com.helpline.helplineapi.data.contract.post.all;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.job.JobContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetAllJobsResponse extends BaseResponse {
    private List<JobContract> jobs;
}
