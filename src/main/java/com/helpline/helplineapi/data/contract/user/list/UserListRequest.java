package com.helpline.helplineapi.data.contract.user.list;

import com.helpline.helplineapi.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserListRequest {
    private UserTypeEnum type;
}
