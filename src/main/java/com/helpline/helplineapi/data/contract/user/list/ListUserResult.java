package com.helpline.helplineapi.data.contract.user.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResult {
    private UUID id;

    private String name;

    private String email;

    private String document;
}
