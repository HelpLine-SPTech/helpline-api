package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.pix.PixInformationContract;
import com.helpline.helplineapi.entities.pix.PixInformation;

public class PixInformationMapper {
    public static PixInformationContract toContract(PixInformation self) {
        var dto = new PixInformationContract();
        dto.setKey(self.getKey());
        dto.setType(self.getType());

        return dto;
    }

    public static PixInformation toEntity(PixInformationContract self) {
        var entity = new PixInformation();
        entity.setKey(self.getKey());
        entity.setType(self.getType());

        return entity;
    }
}
