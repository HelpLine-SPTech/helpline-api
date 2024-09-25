package com.helpline.helplineapi.data.contract.campaign.get.list;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ListCampaignResponse extends BaseResponse {
    private List<CampaignContract> campaigns;

    private int totalPages;

    private long totalElements;
}
