package com.helpline.helplineapi.data.contract.campaign.create;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CreateCampaignRequest {

    private UUID ongId;

    private CampaignContract campaign;
}
