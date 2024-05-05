package com.helpline.helplineapi.entities.job;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "subscription")
@Getter @Setter
public class SubscriptionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobEntity job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_id")
    private BaseUserEntity volunteer;
}
