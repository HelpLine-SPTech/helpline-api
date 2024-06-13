package com.helpline.helplineapi.data.contract.user;

import com.helpline.helplineapi.data.contract.address.AddressContract;
import com.helpline.helplineapi.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContract {
    private UUID id;

    private String name;

    private String bio;

    private String email;

    private String document;

    private String profilePicUrl;

    private AddressContract address;

    private String type;

    private List<String> abilities;
}
