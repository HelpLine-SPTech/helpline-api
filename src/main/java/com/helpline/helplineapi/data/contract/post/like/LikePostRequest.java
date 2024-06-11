package com.helpline.helplineapi.data.contract.post.like;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class LikePostRequest {
    private UUID userId;

    private UUID postId;
}
