package com.helpline.helplineapi.repositories;

import com.helpline.helplineapi.entities.file.FileEntity;
import com.helpline.helplineapi.enums.FileTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    boolean existsByOwnerId(UUID ownerId);
    Optional<FileEntity> findByOwnerIdAndType(UUID ownerId, FileTypeEnum type);
}
