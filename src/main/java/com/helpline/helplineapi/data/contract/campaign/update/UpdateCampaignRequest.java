package com.helpline.helplineapi.data.contract.campaign.update;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UpdateCampaignRequest {

    private BaseUserEntity user;

    private UUID campaignId;

    private CampaignContract campaign;
}
