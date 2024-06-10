package com.helpline.helplineapi.data.contract.job.subscribe;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class SubscribeRequest {
    private UUID jobId;

    private UUID userId;
}
