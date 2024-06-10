package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.post.PostContract;
import com.helpline.helplineapi.entities.post.PostEntity;

import java.util.List;

public abstract class PostMapper {

    public static PostContract toDto(PostEntity self) {
        if(self == null) {
            return null;
        }

        var dto = new PostContract();
        dto.setId(self.getId());
        dto.setComments(CommentMapper.toDto(self.getComments()));
        dto.setLikes(getLikes(self));
        dto.setContent(self.getContent());
        dto.setImages(FileMapper.toDto(self.getImages()));
        dto.setAddedAt(self.getAddedAt());

        return dto;
    }

    public static List<PostContract> toDto(List<PostEntity> self) {
        if(self == null) return null;
        return self.stream().map(PostMapper::toDto).toList();
    }

    private static long getLikes(PostEntity self) {
        if(self.getLikes() == null) return 0;
        return self.getLikes().size();
    }
}
