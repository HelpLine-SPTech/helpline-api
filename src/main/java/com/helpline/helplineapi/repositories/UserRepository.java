package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
