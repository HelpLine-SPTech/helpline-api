package com.helpline.helplineapi.services.campaign;

import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignResponse;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CreateCampaignService extends BaseService<CreateCampaignRequest, CreateCampaignResponse> {

    @Override
    protected CreateCampaignResponse processService(CreateCampaignRequest createCampaignRequest) {
        var response = new CreateCampaignResponse();



        return response;
    }

    @Override
    protected CreateCampaignResponse validateService(CreateCampaignRequest createCampaignRequest) {
        return new CreateCampaignResponse();
    }
}
