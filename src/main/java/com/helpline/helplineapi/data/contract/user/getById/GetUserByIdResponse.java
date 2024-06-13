package com.helpline.helplineapi.data.contract.user.getById;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.user.UserContract;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserByIdResponse extends BaseResponse {
    private UserContract user;
}
