package com.helpline.helplineapi.data.contract.job.create;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.job.JobContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateJobResponse extends BaseResponse {
    private JobContract job;
}
