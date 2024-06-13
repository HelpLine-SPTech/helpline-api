package com.helpline.helplineapi.data.contract.user.updateName;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UpdateUserNameRequest {
    private String name;

    private UUID userId;
}
