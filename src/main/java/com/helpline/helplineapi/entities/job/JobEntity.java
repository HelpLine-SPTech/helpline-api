package com.helpline.helplineapi.entities.job;

import com.helpline.helplineapi.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.Set;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "job")
@Table(name = "job")
public class JobEntity extends BaseEntity {
    /**
     * Descrição da vaga
     */
    private String description;

    /**
     * Habilidades necessárias para a vaga
     */
    @Column(columnDefinition = "text[]")
    private Set abilities;

    /**
     * Lista de candidatos à vaga
     */
    @ElementCollection
    @CollectionTable(
            name = "inscription",
            joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "candidates")
    private List<UUID> candidates;
}
