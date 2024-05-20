package com.helpline.helplineapi.data.contract.user.profilepic;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProfilePicResponse extends BaseResponse {
    private String url;
}
