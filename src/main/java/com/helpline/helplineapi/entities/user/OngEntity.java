package com.helpline.helplineapi.entities.user;

import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.job.JobEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
}
