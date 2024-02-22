package com.helpline.helplineapi.data.contract.auth.register;

import com.helpline.helplineapi.entities.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {
    private UserEntity savedUser;
}
