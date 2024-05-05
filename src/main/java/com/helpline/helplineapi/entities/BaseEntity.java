package com.helpline.helplineapi.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Classe abstrata que representa uma entidade basica no banco
 */
@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    /**
     * Id da entidade
     */
    @Id
    @UuidGenerator
    private UUID id;

    /**
     * Data de criação no banco
     */
    @CreationTimestamp
    private LocalDateTime addedAt;
}
