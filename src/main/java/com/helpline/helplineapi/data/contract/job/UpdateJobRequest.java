package com.helpline.helplineapi.data.contract.job;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateJobRequest {

    private JobContract job;

    private UUID ongId;

    private UUID jobId;


}
