package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
