package com.helpline.helplineapi.data.contract.chat;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class GetChatUsersRequest {
  private UUID userId;
}
