package com.helpline.helplineapi.data.contract.job;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobResponse extends BaseResponse {

    private JobContract job;

}
