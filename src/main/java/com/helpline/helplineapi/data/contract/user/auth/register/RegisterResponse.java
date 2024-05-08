package com.helpline.helplineapi.data.contract.user.auth.register;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse extends BaseResponse {
    private BaseUserEntity savedUser;
}
