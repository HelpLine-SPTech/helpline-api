package com.helpline.helplineapi.data.contract.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {
    private UUID id;

    private String name;

    private String email;

    private String document;
}
