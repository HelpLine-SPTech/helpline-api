package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity> findByUserId(UUID id);
}
