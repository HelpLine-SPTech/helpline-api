package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByEmail(String email);

    UserEntity save(UserEntity user);

    Collection<UserEntity> findByType(UserTypeEnum type);
}
