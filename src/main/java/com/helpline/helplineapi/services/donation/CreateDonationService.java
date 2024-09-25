package com.helpline.helplineapi.services.donation;

import com.helpline.helplineapi.data.contract.donations.DonationContract;
import com.helpline.helplineapi.data.contract.donations.create.CreateDonationRequest;
import com.helpline.helplineapi.data.contract.donations.create.CreateDonationResponse;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.DonationMapper;
import com.helpline.helplineapi.repositories.CampaignRepository;
import com.helpline.helplineapi.repositories.DonationRepository;
import com.helpline.helplineapi.repositories.OngRepository;
import com.helpline.helplineapi.repositories.UserRepository;
import com.helpline.helplineapi.services.BaseService;
import com.helpline.helplineapi.services.campaign.CreateCampaignService;
import org.springframework.stereotype.Service;

@Service
public class CreateDonationService extends BaseService<CreateDonationRequest, CreateDonationResponse> {

    private CampaignEntity campaign;

    private BaseUserEntity donor;


    private final CampaignRepository campaignRepository;

    private final DonationRepository donationRepository;

    public CreateDonationService(CampaignRepository campaignRepository, DonationRepository donationRepository) {
        this.campaignRepository = campaignRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    protected CreateDonationResponse processService(CreateDonationRequest request) {
        var response = new CreateDonationResponse();

        var newDonation = this.getDonationResult(request);

        var inserted = donationRepository.save(newDonation);
        response.setDonation(DonationMapper.toContract(inserted));

        return response;
    }

    @Override
    protected CreateDonationResponse validateService(CreateDonationRequest request) {
        var response = new CreateDonationResponse();

        campaignRepository.findById(request.getDonation().getCampaignId())
                .ifPresentOrElse(
                        campaignEntity -> campaign = campaignEntity,
                        () -> response.addError(ErrorCodeEnum.NOT_FOUND_ERROR));

        return response;
    }

    private DonationEntity getDonationResult(CreateDonationRequest request) {
        var contract = request.getDonation();

        var entity = new DonationEntity();
        entity.setAmount(contract.getAmount());
        entity.setType(campaign.getType());
        entity.setCampaign(campaign);
        entity.setDonor(request.getDonor());
        entity.setQuantity(contract.getQuantity());

        return entity;
    }
}
