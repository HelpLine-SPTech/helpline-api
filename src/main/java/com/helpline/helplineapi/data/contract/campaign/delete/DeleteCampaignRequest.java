package com.helpline.helplineapi.data.contract.campaign.delete;

import com.helpline.helplineapi.entities.user.BaseUserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class DeleteCampaignRequest {
    private BaseUserEntity user;

    private UUID campaignId;
}
