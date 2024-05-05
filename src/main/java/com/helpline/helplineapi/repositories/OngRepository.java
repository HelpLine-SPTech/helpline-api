package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.user.OngEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OngRepository extends JpaRepository<OngEntity, UUID> {

}
