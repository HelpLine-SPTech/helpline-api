package com.helpline.helplineapi.entities.user;

import com.helpline.helplineapi.entities.job.JobEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class OngEntity extends BaseUserEntity {

    @OneToMany(mappedBy = "ong", fetch = FetchType.LAZY)
    private Set<JobEntity> jobs;
}
