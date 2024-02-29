package com.helpline.helplineapi.data.contract.auth.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private String email;

    private String password;
}
