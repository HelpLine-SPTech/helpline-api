package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.address.AddressContract;
import com.helpline.helplineapi.entities.address.AddressEntity;

public abstract class AddressMapper {
    public static AddressEntity toEntity(AddressContract self) {
        var entity = new AddressEntity();
        entity.setId(self.getId());
        entity.setState(self.getState());
        entity.setStreet(self.getStreet());
        entity.setNumber(self.getNumber());
        entity.setComplement(self.getComplement());
        entity.setCity(self.getCity());
        entity.setZipCode(self.getZipCode());
        entity.setNeighborhood(self.getNeighborhood());

        return entity;
    }

    public static AddressContract toDto(AddressEntity self) {
        var dto = new AddressContract();
        dto.setId(self.getId());
        dto.setState(self.getState());
        dto.setStreet(self.getStreet());
        dto.setNumber(self.getNumber());
        dto.setComplement(self.getComplement());
        dto.setCity(self.getCity());
        dto.setZipCode(self.getZipCode());
        dto.setNeighborhood(self.getNeighborhood());

        return dto;
    }
}
