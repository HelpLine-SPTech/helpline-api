package com.helpline.helplineapi.entities.address;

import com.helpline.helplineapi.entities.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um endereço
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
public class AddressEntity extends BaseEntity {
    /**
     * CEP.
     */
    private String zipCode;

    /**
     * Estado.
     */
    private String state;

    /**
     * Cidade.
     */
    private String city;

    /**
     * Bairro.
     */
    private String neighborhood;

    /**
     * Rua.
     */
    private String street;

    /**
     * Número.
     */
    private String number;

    /**
     * Complemento.
     */
    private String complement;
}
