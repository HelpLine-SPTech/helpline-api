package com.helpline.helplineapi.data.contract.post;

import com.helpline.helplineapi.data.contract.user.UserContract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentContract {
    private UUID id;

    private UserContract user;

    private LocalDateTime addedAt;

    private String content;
}
