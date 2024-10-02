package com.helpline.helplineapi.data.contract.campaign;

import com.helpline.helplineapi.data.contract.donations.DonationContract;
import com.helpline.helplineapi.enums.BadgeTypeEnum;
import com.helpline.helplineapi.enums.CampaignTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class CampaignContract {

    private UUID id;

    private String title;

    private String description;

    private UUID ongId;

    private List<DonationContract> donations;

    private CampaignTypeEnum type;

    private BadgeTypeEnum badgeType;

    private Double monetaryGoal;

    private Integer donationGoal;
}
