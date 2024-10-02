package com.helpline.helplineapi.entities.donation;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.campaign.CampaignEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.CampaignTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
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
    private Long amount;

    /**
     * Quantidade de itens doados
     */
    private Integer quantity;

    /**
     * Tipo da campanha (financeira ou de itens)
     */
    private CampaignTypeEnum type;

    /**
     * Campanha na qual a doacao foi realizada
     */
    @ManyToOne()
    private CampaignEntity campaign;

    /**
     * Usuario que realizou a doacao
     */
    @ManyToOne()
    @JoinColumn(name = "donor_id")
    private UserEntity donor;
}
