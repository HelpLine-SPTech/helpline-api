package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.user.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface BaseUserRepository extends JpaRepository<BaseUserEntity, UUID> {
    UserDetails findByEmail(String email);

    List<BaseUserEntity> findByNameContainsIgnoreCase(String name);
}
