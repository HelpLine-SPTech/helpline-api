package com.helpline.helplineapi.data.contract.user.getById;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class GetUserByIdRequest {
    private UUID id;
}
