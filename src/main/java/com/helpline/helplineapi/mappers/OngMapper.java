package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.user.OngContract;
import com.helpline.helplineapi.entities.user.OngEntity;

public abstract class OngMapper {
    public static OngContract toDto(OngEntity entity) {
        var dto = new OngContract();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDocument(entity.getDocument());
        dto.setEmail(entity.getEmail());

        return dto;
    }
}
