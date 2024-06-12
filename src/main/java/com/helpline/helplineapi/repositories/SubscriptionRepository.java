package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.job.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
    List<SubscriptionEntity> findByVolunteerId(UUID id);
}
