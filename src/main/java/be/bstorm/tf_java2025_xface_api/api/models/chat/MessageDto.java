package be.bstorm.tf_java2025_xface_api.api.models.chat;

import be.bstorm.tf_java2025_xface_api.dl.entities.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDto(
        Long id,
        UUID conversationId,
        UUID senderId,
        String senderName,
        LocalDateTime sendAt
) {

    public static MessageDto fromMessage(Message m){
        return new MessageDto(
                m.getId(),
                m.getConversation().getId(),
                m.getSender().getId(),
                m.getSender().getFirstName(),
                m.getCreatedAt()
        );
    }
}
