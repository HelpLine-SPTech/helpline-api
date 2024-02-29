package com.helpline.helplineapi.data.contract.auth.login;

import com.helpline.helplineapi.data.contract.auth.BaseResponse;
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
}
