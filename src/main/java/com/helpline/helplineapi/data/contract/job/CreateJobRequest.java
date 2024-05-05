package com.helpline.helplineapi.data.contract.job;

import com.helpline.helplineapi.data.contract.user.UserContract;
import com.helpline.helplineapi.entities.user.OngEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CreateJobRequest {

    private JobContract job;

    private UUID ongId;
}
