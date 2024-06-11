package com.helpline.helplineapi.data.contract.post.list;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.post.PostContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ListPostResponse extends BaseResponse {
    private List<PostContract> posts;
}
