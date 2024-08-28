package com.helpline.helplineapi.entities.campaing;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.donation.DonationEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.enums.BadgeTypeEnum;
import com.helpline.helplineapi.enums.CampaignTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "campaign")
@Entity(name = "campaign")
public class CampaignEntity extends BaseEntity {

    private OngEntity ong;

    @OneToMany
    private List<DonationEntity> donations;

    @Enumerated(EnumType.STRING)
    private CampaignTypeEnum type;

    private BadgeTypeEnum badgeType;

    private Double monetaryGoal;

    private Integer donationGoal;
}
