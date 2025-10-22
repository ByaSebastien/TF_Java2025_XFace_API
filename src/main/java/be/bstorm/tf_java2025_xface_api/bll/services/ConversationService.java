package be.bstorm.tf_java2025_xface_api.bll.services;

import be.bstorm.tf_java2025_xface_api.dl.entities.Conversation;
import be.bstorm.tf_java2025_xface_api.dl.entities.Message;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;

import java.util.List;
import java.util.UUID;

public interface ConversationService {

    Conversation getOrCreateConversation(User connectedUser, UUID userId);
    Message saveMessage(UUID conversationId, Message message);
    List<Message> getMessages(UUID conversationId);
}