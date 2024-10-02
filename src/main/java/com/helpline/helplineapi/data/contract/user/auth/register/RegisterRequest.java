package com.helpline.helplineapi.data.contract.user.auth.register;

import com.helpline.helplineapi.data.contract.address.AddressContract;
import com.helpline.helplineapi.enums.UserRole;
import com.helpline.helplineapi.enums.UserTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;

    @NotBlank
    private String password;

    private String name;

    private String document;

    private UserTypeEnum type;

    private UserRole role;

    private AddressContract address;

    private List<String> abilities;
}
