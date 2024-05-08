package com.helpline.helplineapi.entities.job;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.address.AddressEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa uma postagem de uma vaga
 */
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
    @ElementCollection
    private List<String> abilities;

    /**
     * Endereço da vaga
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_address")
    private AddressEntity address;

    /**
     * Data da vaga
     */
    private LocalDateTime date;

    /**
     * Quantidade de vagas disponiveis
     */
    private long amount;

    /**
     * Usuários voluntarios a vaga
     */
    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<SubscriptionEntity> volunteers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private OngEntity ong;
}
