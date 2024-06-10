package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.post.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
