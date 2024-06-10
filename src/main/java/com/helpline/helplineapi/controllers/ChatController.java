package com.helpline.helplineapi.controllers;
import com.helpline.helplineapi.data.contract.chat.GetChatUsersRequest;
import com.helpline.helplineapi.data.contract.chat.GetChatUsersResponse;
import com.helpline.helplineapi.entities.chat.ChatMessage;
import com.helpline.helplineapi.entities.chat.ChatNotification;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.dashboard.chat.ChatMessageService;
import com.helpline.helplineapi.services.dashboard.chat.GetOngVolunteersService;
import com.helpline.helplineapi.services.dashboard.chat.GetVolunteerOngsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatMessageService chatMsgService;
  private final GetOngVolunteersService getOngVolunteersService;
  private final GetVolunteerOngsService getVolunteerOngsService;

  @MessageMapping("/chat")
  public void processMessage(@Payload ChatMessage chatMessage){
    ChatMessage savedMsg = chatMsgService.save(chatMessage);
    messagingTemplate.convertAndSendToUser(
      chatMessage.getRecipientId(), "/queue/messages",
      new ChatNotification(
        savedMsg.getId(),
        savedMsg.getSenderId(),
        savedMsg.getRecipientId(),
        savedMsg.getContent()
      )
    );
  }

  @GetMapping("/messages/{senderId}/{recipientId}")
  public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId){

    return ResponseEntity.status(200).body(chatMsgService.findChatMessages(senderId, recipientId));
  }

  @GetMapping("/users")
  public ResponseEntity<GetChatUsersResponse> getOngVolunteers(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser) {
    var request = new GetChatUsersRequest();
    request.setUserId(requesterUser.getId());
    return getOngVolunteersService.process(request);
  }

  @GetMapping("/ongs")
  public ResponseEntity<GetChatUsersResponse> getVolunteerOngs(@RequestAttribute("RequesterUser") BaseUserEntity requesterUser) {
    var request = new GetChatUsersRequest();
    request.setUserId(requesterUser.getId());
    return getVolunteerOngsService.process(request);
  }
}
