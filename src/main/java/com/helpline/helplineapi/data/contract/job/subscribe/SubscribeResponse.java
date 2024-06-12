package com.helpline.helplineapi.data.contract.job.subscribe;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class SubscribeResponse extends BaseResponse {
    private UUID subscriptionId;
}
