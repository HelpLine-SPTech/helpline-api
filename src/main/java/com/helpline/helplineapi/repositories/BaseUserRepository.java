package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public interface BaseUserRepository extends JpaRepository<BaseUserEntity, UUID> {
    UserDetails findByEmail(String email);
}
