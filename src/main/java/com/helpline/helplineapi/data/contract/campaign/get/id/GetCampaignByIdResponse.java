package com.helpline.helplineapi.data.contract.campaign.get.id;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetCampaignByIdResponse extends BaseResponse {
    private CampaignContract campaign;
}
