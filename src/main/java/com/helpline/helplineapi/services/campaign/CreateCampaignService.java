package com.helpline.helplineapi.services.campaign;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignRequest;
import com.helpline.helplineapi.data.contract.campaign.create.CreateCampaignResponse;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.CampaignMapper;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreateCampaignService extends BaseService<CreateCampaignRequest, CreateCampaignResponse> {

    private OngEntity ong;

    private final OngRepository ongRepository;

    private final CampaignRepository campaignRepository;

    public CreateCampaignService(OngRepository ongRepository, CampaignRepository campaignRepository) {
        this.ongRepository = ongRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    protected CreateCampaignResponse processService(CreateCampaignRequest request) {
        var response = new CreateCampaignResponse();

        var newCampaign = getCampaignResult(request.getCampaign());

        var inserted = campaignRepository.save(newCampaign);

        response.setCampaign(CampaignMapper.toContract(inserted));
        return response;
    }

    @Override
    protected CreateCampaignResponse validateService(CreateCampaignRequest request) {
        var response = new CreateCampaignResponse();

        this.ong = getOng(request, response);

        return response;
    }

    private OngEntity getOng(CreateCampaignRequest request, CreateCampaignResponse response) {
        var ongOpt = this.ongRepository.findById(request.getOngId());

        if(ongOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return null;
        }

        return ongOpt.get();
    }

    private CampaignEntity getCampaignResult(CampaignContract contract) {
        var campaign = new CampaignEntity();
        campaign.setTitle(contract.getTitle());
        campaign.setDescription(contract.getDescription());
        campaign.setType(contract.getType());
        campaign.setBadgeType(contract.getBadgeType());
        campaign.setDonationGoal(contract.getDonationGoal());
        campaign.setMonetaryGoal(contract.getMonetaryGoal());
        campaign.setDonationGoal(contract.getDonationGoal());
//        campaign.setDonations(new ArrayList<>());
        campaign.setOng(this.ong);

        return campaign;
    }
}
