package com.helpline.helplineapi.data.contract.search;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.user.UserContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SearchUserResponse extends BaseResponse {
    private List<UserContract> users;
}
