package com.helpline.helplineapi.data.contract.job.list;

import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class GetJobByIdRequest {
    private UUID jobId;

    private UUID requesterUserId;
}
