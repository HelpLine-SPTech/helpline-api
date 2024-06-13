package com.helpline.helplineapi.data.contract.post.getByUserId;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class GetPostsByUserIdRequest {
    private UUID userId;

    private UUID requesterUserId;
}
