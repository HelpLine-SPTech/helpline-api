package com.helpline.helplineapi.services.campaign;

import com.helpline.helplineapi.data.contract.campaign.update.UpdateCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.update.UpdateCampaignResponse;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.CampaignMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCampaignService extends BaseService<UpdateCampaignRequest, UpdateCampaignResponse> {

    @Autowired
    private BaseUserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    private CampaignEntity campaign;

    private BaseUserEntity user;

    @Override
    protected UpdateCampaignResponse processService(UpdateCampaignRequest request) {
        var updated = CampaignMapper.merge(campaign, request.getCampaign());

        campaignRepository.save(updated);

        return new UpdateCampaignResponse();
    }

    @Override
    protected UpdateCampaignResponse validateService(UpdateCampaignRequest request) {
        var response = new UpdateCampaignResponse();

        userRepository
                .findById(request.getUser().getId())
                .ifPresentOrElse((user) -> {
                    this.user = user;
                    if(!(user instanceof OngEntity)) {
                        response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
                    }
                }, () -> response.addError(ErrorCodeEnum.NOT_FOUND_ERROR));

        if(!response.getSuccess()) return response;

        campaignRepository
                .findById(request.getCampaignId())
                .ifPresentOrElse((campaign) -> {
                    this.campaign = campaign;
                    if (campaign.getOng().getId() != user.getId()) {
                        response.addError(ErrorCodeEnum.OPERATION_NOT_PERMITTED);
                    }
                }, () -> response.addError(ErrorCodeEnum.NOT_FOUND_ERROR));

        return response;
    }
}
