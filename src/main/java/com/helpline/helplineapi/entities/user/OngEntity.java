package com.helpline.helplineapi.entities.user;

import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.job.JobEntity;
import com.helpline.helplineapi.entities.pix.PixInformation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
public class OngEntity extends BaseUserEntity {

    @OneToMany(mappedBy = "ong", fetch = FetchType.LAZY)
    private Set<JobEntity> jobs;

    @OneToMany(mappedBy = "ong", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;

    @OneToOne(fetch = FetchType.LAZY)
    private PixInformation pixInfo;
}
