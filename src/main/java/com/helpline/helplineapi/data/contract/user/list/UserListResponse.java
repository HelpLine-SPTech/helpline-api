package com.helpline.helplineapi.data.contract.user.list;

import com.helpline.helplineapi.data.contract.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserListResponse extends BaseResponse {
    private List<ListUserResult> users;
}
