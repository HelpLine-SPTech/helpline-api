package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.post.LikeContract;
import com.helpline.helplineapi.entities.post.LikeEntity;

import java.util.List;

public abstract class LikeMapper {
    public static LikeContract toDto(LikeEntity self) {
        if(self == null) return null;
        var dto = new LikeContract();
        dto.setUserId(self.getUser().getId());

        return dto;
    }

    public static List<LikeContract> toDto(List<LikeEntity> self) {
        if(self == null) return null;
        return self.stream().map(LikeMapper::toDto).toList();
    }
}
