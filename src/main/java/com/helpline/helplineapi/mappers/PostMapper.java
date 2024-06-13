package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.post.LikeContract;
import com.helpline.helplineapi.data.contract.post.PostContract;
import com.helpline.helplineapi.entities.post.PostEntity;

import java.util.List;
import java.util.UUID;

public abstract class PostMapper {

    public static PostContract toDto(PostEntity self, UUID requesterUserId) {
        if(self == null) {
            return null;
        }

        var dto = new PostContract();
        dto.setId(self.getId());
        dto.setComments(CommentMapper.toDto(self.getComments()));
        dto.setLikes(LikeMapper.toDto(self.getLikes()));
        dto.setContent(self.getContent());
        dto.setImages(FileMapper.toDto(self.getImages()));
        dto.setAddedAt(self.getAddedAt());
        dto.setUser(UserMapper.toUserResult(self.getUser()));
        dto.setLiked(dto.getLikes() != null && dto.getLikes().stream().anyMatch(l -> l.getUserId().equals(requesterUserId)));

        return dto;
    }

    public static List<PostContract> toDto(List<PostEntity> self, UUID requesterUserId) {
        if(self == null) return null;
        return self.stream().map(post -> PostMapper.toDto(post, requesterUserId)).toList();
    }
}
