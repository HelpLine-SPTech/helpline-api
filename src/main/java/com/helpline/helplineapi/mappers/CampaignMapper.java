package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.campaign.CampaignContract;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;

import java.util.List;
import java.util.UUID;

public class CampaignMapper {
    public static CampaignContract toContract(CampaignEntity entity) {
        if(entity == null) return null;

        var campaign = new CampaignContract();

        campaign.setId(entity.getId());
        campaign.setTitle(entity.getTitle());
        campaign.setDescription(entity.getDescription());
        campaign.setBadgeType(entity.getBadgeType());
        campaign.setType(entity.getType());
        campaign.setMonetaryGoal(entity.getMonetaryGoal());
        campaign.setDonationGoal(entity.getDonationGoal());
        campaign.setOngId(entity.getOng().getId());
//        campaign.setDonations(DonationMapper.toContract(entity.getDonations()));

        return campaign;
    }

    public static List<CampaignContract> toContract(List<CampaignEntity> self) {
        return self.stream().map(CampaignMapper::toContract).toList();
    }

    public static CampaignEntity merge(CampaignEntity old, CampaignContract updated) {
        old.setDescription(updated.getDescription());
        old.setTitle(updated.getTitle());
        old.setMonetaryGoal(updated.getMonetaryGoal());
        old.setDonationGoal(updated.getDonationGoal());
        old.setBadgeType(updated.getBadgeType());
        old.setType(updated.getType());

        return old;
    }
}
