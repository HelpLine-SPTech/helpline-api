package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.post.CommentContract;
import com.helpline.helplineapi.entities.post.CommentEntity;

import java.util.List;

public abstract class CommentMapper {
    public static CommentContract toDto(CommentEntity self) {
        if(self == null) {
            return null;
        }

        var dto = new CommentContract();
        dto.setId(self.getId());
        dto.setContent(self.getContent());
        dto.setAddedAt(self.getAddedAt());
        dto.setUser(UserMapper.toUserResult(self.getUser()));

        return dto;
    }

    public static List<CommentContract> toDto(List<CommentEntity> self) {
        if(self == null) return null;
        return self.stream().map(CommentMapper::toDto).toList();
    }
}
