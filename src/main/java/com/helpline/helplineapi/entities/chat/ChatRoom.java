package com.helpline.helplineapi.entities.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class ChatRoom {
    @Id
    @UuidGenerator
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

}
