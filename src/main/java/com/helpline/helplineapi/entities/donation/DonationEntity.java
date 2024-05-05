package com.helpline.helplineapi.entities.donation;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa uma doacao de um usuario para uma ong
 */
@Getter
@Setter
@Table(name = "donation")
@Entity(name = "donation")
public class DonationEntity extends BaseEntity {

    /**
     * Quantidade doada em inteiro
     */
    private long amount;

    /**
     * Ong que recebeu a doacao
     */
    @ManyToOne()
    @JoinColumn(name = "ong_id")
    private OngEntity receiver;

    /**
     * Usuario que realizou a doacao
     */
    @ManyToOne()
    @JoinColumn(name = "donor_id")
    private UserEntity donor;
}
