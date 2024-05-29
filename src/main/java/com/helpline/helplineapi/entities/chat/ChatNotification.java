package com.helpline.helplineapi.entities.chat;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    @Id
    @UuidGenerator
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
