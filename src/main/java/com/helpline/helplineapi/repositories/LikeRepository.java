package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.post.LikeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {
    @Transactional
    void deleteByUserIdAndPostId(UUID userId, UUID postId);
}
