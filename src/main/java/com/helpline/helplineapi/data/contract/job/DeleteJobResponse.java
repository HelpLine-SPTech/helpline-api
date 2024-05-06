package com.helpline.helplineapi.data.contract.job;
import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteJobResponse extends BaseResponse {
    private JobContract job;

}
