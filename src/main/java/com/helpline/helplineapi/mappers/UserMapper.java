package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.user.UserContract;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.entities.user.OngEntity;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.UserTypeEnum;

import java.util.List;

public abstract class UserMapper {
    public static UserContract toUserResult(BaseUserEntity self) {
        if(self == null) return null;

        var dto = new UserContract();
        dto.setId(self.getId());
        dto.setName(self.getName());
        dto.setDocument(self.getDocument());
        dto.setAddress(AddressMapper.toDto(self.getAddress()));
        dto.setBio(self.getBio());
        dto.setEmail(self.getEmail());
        dto.setProfilePicUrl(self.getProfilePicUrl());

        if(self instanceof UserEntity) {
            dto.setType("UserEntity");
            dto.setAbilities(((UserEntity) self).getAbilities());
        } else {
            dto.setType("OngEntity");
        }

        return dto;
    }

    public static List<UserContract> toUserResult(List<BaseUserEntity> self) {
        return self.stream().map(UserMapper::toUserResult).toList();
    }
}
