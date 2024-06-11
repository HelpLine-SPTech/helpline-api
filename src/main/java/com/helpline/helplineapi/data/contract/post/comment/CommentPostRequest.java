package com.helpline.helplineapi.data.contract.post.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CommentPostRequest {
    private UUID userId;

    private UUID postId;

    private String content;
}
