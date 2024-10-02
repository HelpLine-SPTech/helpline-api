package com.helpline.helplineapi.mappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.helpline.helplineapi.data.contract.donations.DonationContract;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.UserEntity;

public class DonationMapper {
    public static DonationContract toContract(DonationEntity entity) {
        var donation = new DonationContract();

        donation.setId(entity.getId());
        donation.setAmount(entity.getAmount());
        donation.setDonationDate(entity.getAddedAt());
        donation.setQuantity(entity.getQuantity());
        donation.setDonorId(entity.getDonor().getId());
        donation.setCampaignId(entity.getCampaign().getId());

        return donation;
    }

    public static List<DonationContract> toContract(List<DonationEntity> entities) {
        return entities.stream()
                .map(DonationMapper::toContract)
                .collect(Collectors.toList());
    }

    public static DonationEntity toEntity(DonationContract donation, CampaignEntity campaign, UserEntity donor) {
        var entity = new DonationEntity();

        entity.setId(donation.getId());
        entity.setAmount(donation.getAmount());
        entity.setCampaign(campaign);
        entity.setType(campaign.getType());
        entity.setQuantity(donation.getQuantity());
        entity.setDonor(donor);

        return entity;
    }
}
