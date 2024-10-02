package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CampaignRepository extends JpaRepository<CampaignEntity, UUID> {
    Page<CampaignEntity> findAllByOngId(UUID ongId, Pageable pageable);
}
