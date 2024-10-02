package com.helpline.helplineapi.data.contract.campaign.create;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCampaignResponse extends BaseResponse {
    private CampaignContract campaign;
}
