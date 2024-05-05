package com.helpline.helplineapi.data.contract.dashboard;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SummaryResponse extends BaseResponse {

    private SummaryData summary;
}
