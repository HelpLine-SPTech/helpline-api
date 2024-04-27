package com.helpline.helplineapi.data.contract.user.auth.login;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.user.UserResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse extends BaseResponse {
    private String token;

    private UserResult user;
}
