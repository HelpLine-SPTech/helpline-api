package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.job.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    long countByOngId(UUID ongId);
}