package com.helpline.helplineapi.data.contract.chat;

import com.helpline.helplineapi.data.contract.BaseResponse;
import com.helpline.helplineapi.data.contract.user.UserContract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetChatUsersResponse extends BaseResponse {
  private List<UserContract> users;
}