package be.bstorm.tf_java2025_xface_api.api.hubs;

import be.bstorm.tf_java2025_xface_api.api.models.chat.MessageDto;
import be.bstorm.tf_java2025_xface_api.api.models.chat.MessageForm;
import be.bstorm.tf_java2025_xface_api.bll.services.ConversationService;
import be.bstorm.tf_java2025_xface_api.dl.entities.Conversation;
import be.bstorm.tf_java2025_xface_api.dl.entities.Message;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatHub {

    private final ConversationService conversationService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    @PreAuthorize("isAuthenticated()")
    public void sendMessage(MessageForm form, Principal principal) {
        User connectedUser = (User) principal;

        Conversation conversation = conversationService.getOrCreateConversation(connectedUser, form.receiverId());

        UUID conversationId = conversation.getId();

        Message message = new Message(connectedUser, form.content());

        Message saved = conversationService.saveMessage(conversationId,message);

        messagingTemplate.convertAndSend("/topic/conversations." + conversationId, MessageDto.fromMessage(saved));
    }
}