package com.helpline.helplineapi.data.contract.post.comment;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.post.CommentContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentPostResponse extends BaseResponse {
    private CommentContract comment;
}
