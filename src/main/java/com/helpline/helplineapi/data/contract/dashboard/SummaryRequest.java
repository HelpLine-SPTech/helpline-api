package com.helpline.helplineapi.data.contract.dashboard;

import com.helpline.helplineapi.entities.user.BaseUserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class SummaryRequest {
    private UUID ongId;
}
