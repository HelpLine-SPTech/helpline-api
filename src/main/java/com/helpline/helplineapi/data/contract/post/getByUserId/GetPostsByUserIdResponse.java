package com.helpline.helplineapi.data.contract.post.getByUserId;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.post.PostContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetPostsByUserIdResponse extends BaseResponse {
    private List<PostContract> posts;
}
