package com.helpline.helplineapi.services.campaign;

import com.helpline.helplineapi.data.contract.campaign.delete.DeleteCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.delete.DeleteCampaignResponse;
import com.helpline.helplineapi.data.contract.campaign.update.UpdateCampaignResponse;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCampaignService extends BaseService<DeleteCampaignRequest, DeleteCampaignResponse> {

    @Autowired
    private BaseUserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    private CampaignEntity campaign;

    private BaseUserEntity user;

    @Override
    protected DeleteCampaignResponse processService(DeleteCampaignRequest request) {
        var response = new DeleteCampaignResponse();

        campaignRepository.delete(campaign);

        return response;
    }

    @Override
    protected DeleteCampaignResponse validateService(DeleteCampaignRequest request) {
        var response = new DeleteCampaignResponse();

        userRepository
                .findById(request.getUser().getId())
                .ifPresentOrElse((user) -> {
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
