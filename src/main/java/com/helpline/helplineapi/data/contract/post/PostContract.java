package com.helpline.helplineapi.data.contract.post;

import com.helpline.helplineapi.data.contract.file.FileContract;
import com.helpline.helplineapi.data.contract.user.UserContract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class PostContract {
    private UUID id;

    private String content;

    private List<LikeContract> likes;

    private boolean isLiked;

    private List<FileContract> images;

    private List<CommentContract> comments;

    private UserContract user;

    private LocalDateTime addedAt;

}

