package com.helpline.helplineapi.data.contract.user.auth.register;

import com.helpline.helplineapi.enums.UserRole;
import com.helpline.helplineapi.enums.UserTypeEnum;

public record RegisterRequest(String email, String password, String document, UserTypeEnum type, UserRole role) {
}
