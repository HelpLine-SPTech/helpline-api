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
        dto.setId(job.getId());
        dto.setDescription(job.getDescription());
        dto.setAbilities(job.getAbilities());
        dto.setAddress(AddressMapper.toDto(job.getAddress()));
        dto.setDate(job.getDate());
        dto.setAmount(job.getAmount());

        return dto;
    }

    public static List<JobContract> toDto(List<JobEntity> jobs) {
        return jobs.stream().map(JobMapper::toDto).toList();
    }

    public static JobEntity toEntity(JobContract self) {
        var entity = new JobEntity();
        entity.setId(self.getId());
        entity.setDescription(self.getDescription());
        entity.setAbilities(self.getAbilities());
        entity.setAddress(AddressMapper.toEntity(self.getAddress()));
        entity.setDate(self.getDate());
        entity.setAmount(self.getAmount());

        return entity;
    }

    public static JobEntity merge(JobContract self, JobEntity entity) {
        entity.setDescription(self.getDescription());
        entity.setAbilities(self.getAbilities());
        entity.setAddress(AddressMapper.toEntity(self.getAddress()));
        entity.setDate(self.getDate());
        entity.setAmount(self.getAmount());

        return entity;
    }
}
