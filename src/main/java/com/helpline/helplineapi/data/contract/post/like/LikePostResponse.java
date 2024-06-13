package com.helpline.helplineapi.data.contract.post.like;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.post.LikeContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LikePostResponse extends BaseResponse {
    private LikeContract like;
}
