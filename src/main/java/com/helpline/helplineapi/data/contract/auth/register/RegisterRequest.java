package com.helpline.helplineapi.data.contract.auth.register;

import com.helpline.helplineapi.enums.UserRole;

public record RegisterRequest(String email, String password, UserRole role) {
}
