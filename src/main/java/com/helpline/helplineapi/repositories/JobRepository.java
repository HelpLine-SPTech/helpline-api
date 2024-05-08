package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.job.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    long countByOngId(UUID ongId);

    Page<JobEntity> findAllByOngId(UUID ongId, Pageable pageable);

    void deleteById(UUID id);
}
