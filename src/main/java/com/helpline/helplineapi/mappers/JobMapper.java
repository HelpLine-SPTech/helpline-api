package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.job.*;
import com.helpline.helplineapi.entities.job.JobEntity;

import java.util.List;

/**
 * Maps the Job entity
 */
public abstract class JobMapper {
    public static JobContract toDto(JobEntity job) {
        var dto = new JobContract();
        if(job.getId() != null) {
            dto.setId(job.getId());
        }
        dto.setUser(UserMapper.toUserResult(job.getOng()));
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setAbilities(job.getAbilities());
        dto.setAddress(AddressMapper.toDto(job.getAddress()));
        dto.setDate(job.getDate());
        dto.setAmount(job.getAmount());
        long subscriptions = 0;

        if(job.getVolunteers() != null) {
            subscriptions = job.getVolunteers().size();
        }

        dto.setSubscriptions(subscriptions);

        return dto;
    }

    public static List<JobContract> toDto(List<JobEntity> jobs) {
        return jobs.stream().map(JobMapper::toDto).toList();
    }

    public static JobEntity toEntity(JobContract self) {
        var entity = new JobEntity();
        if(self.getId() != null) {
            entity.setId(self.getId());
        }
        entity.setTitle(self.getTitle());
        entity.setDescription(self.getDescription());
        entity.setAbilities(self.getAbilities());
        entity.setAddress(AddressMapper.toEntity(self.getAddress()));
        entity.setDate(self.getDate());
        entity.setAmount(self.getAmount());

        return entity;
    }

    public static JobEntity merge(JobContract self, JobEntity entity) {
        entity.setTitle(self.getTitle());
        entity.setDescription(self.getDescription());
        entity.setAbilities(self.getAbilities());
        entity.setAddress(AddressMapper.toEntity(self.getAddress()));
        entity.setDate(self.getDate());
        entity.setAmount(self.getAmount());

        return entity;
    }
}
