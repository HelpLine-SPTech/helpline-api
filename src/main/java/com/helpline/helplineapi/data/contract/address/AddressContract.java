package com.helpline.helplineapi.data.contract.address;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class AddressContract {
    private UUID id;

    private String state;

    private String street;

    private String number;

    private String complement;

    private String city;

    private String zipCode;

    private String neighborhood;
}
