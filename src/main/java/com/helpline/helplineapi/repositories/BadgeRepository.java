package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.badge.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BadgeRepository extends JpaRepository<BadgeEntity, UUID> {
}
