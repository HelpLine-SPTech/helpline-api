package com.helpline.helplineapi.data.contract.post.list;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ListPostRequest {
    private UUID requesterUserId;
}
