package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.donation.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DonationRepository extends JpaRepository<DonationEntity, UUID> {
    List<DonationEntity> findByDonorId(UUID donorId);
}
