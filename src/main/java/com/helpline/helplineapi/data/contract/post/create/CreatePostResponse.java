package com.helpline.helplineapi.data.contract.post.create;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.post.PostContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePostResponse extends BaseResponse {
    private PostContract post;
}
