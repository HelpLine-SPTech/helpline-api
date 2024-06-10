package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.donation.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DonationRepository extends JpaRepository<DonationEntity, UUID> {
    List<DonationEntity> findByReceiverIdAndAddedAtBetween(UUID ongId, LocalDateTime startData, LocalDateTime endDate);

    long countByReceiverIdAndAddedAtAfter(UUID id, LocalDateTime startDate);
}
