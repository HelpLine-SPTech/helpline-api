package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;

import java.util.UUID;

public class CampaignMapper {
    public static CampaignContract toContract(CampaignEntity entity, UUID ongId) {
        if(entity == null) return null;

        var campaign = new CampaignContract();

        campaign.setId(entity.getId());
        campaign.setTitle(entity.getTitle());
        campaign.setDescription(entity.getDescription());
        campaign.setBadgeType(entity.getBadgeType());
        campaign.setType(entity.getType());
        campaign.setMonetaryGoal(entity.getMonetaryGoal());
        campaign.setDonationGoal(entity.getDonationGoal());
        campaign.setOngId(ongId);
        campaign.setDonations(DonationMapper.toContract(entity.getDonations()));

        return campaign;
    }
}
