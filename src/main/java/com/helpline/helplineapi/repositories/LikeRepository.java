package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.post.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {

}
