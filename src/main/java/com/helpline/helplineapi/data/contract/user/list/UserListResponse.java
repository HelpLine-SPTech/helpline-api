package com.helpline.helplineapi.data.contract.user.list;

import com.helpline.helplineapi.data.contract.BaseResponse;

import java.util.List;

public class UserListResponse extends BaseResponse {
    private List<ListUserResult> users;
}
