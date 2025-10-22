package be.bstorm.tf_java2025_xface_api.bll.services.impls;

import be.bstorm.tf_java2025_xface_api.bll.services.ConversationService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.ConversationRepository;
import be.bstorm.tf_java2025_xface_api.dal.repositories.MessageRepository;
import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.Conversation;
import be.bstorm.tf_java2025_xface_api.dl.entities.Message;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public Conversation getOrCreateConversation(User connectedUser, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return conversationRepository.findConversation(connectedUser.getId(),userId).orElseGet(() -> {
            Conversation conversation = new Conversation();
            conversation.addUser(connectedUser);
            conversation.addUser(user);
            return conversationRepository.save(conversation);
        });
    }

    @Override
    @Transactional
    public Message saveMessage(UUID conversationId, Message message) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow();

        conversation.addMessage(message);
        conversationRepository.save(conversation);

        return message;
    }

    @Override
    public List<Message> getMessages(UUID conversationId) {
        return messageRepository.findAllByConversationId(conversationId);
    }
}
