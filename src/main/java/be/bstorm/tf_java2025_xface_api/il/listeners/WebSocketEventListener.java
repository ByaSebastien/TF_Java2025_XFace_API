package be.bstorm.tf_java2025_xface_api.il.listeners;

import be.bstorm.tf_java2025_xface_api.bll.services.PresenceService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final PresenceService presenceService;
    private final UserRepository userRepository;

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event){
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        Authentication auth = (Authentication) sha.getUser();
        if (auth == null) return;

        User user = (User) auth.getPrincipal();

        UUID userId = user.getId();

        String sessionId = sha.getSessionId();
        presenceService.register(userId, sessionId);

        userRepository.findUserWithFriendsByUserId(userId).orElseThrow().getFriends().forEach(
            (friend) ->
                messagingTemplate.convertAndSendToUser(
                        friend.getEmail(), "/queue/presence", Map.of("userId", userId, "online", true)
                )
        );
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        Authentication auth = (Authentication) sha.getUser();
        if (auth == null) return;

        User user = (User) auth.getPrincipal();

        UUID userId = user.getId();

        String sessionId = sha.getSessionId();
        presenceService.unregister(userId, sessionId);

        if (!presenceService.isOnline(userId)) {
            userRepository.findUserWithFriendsByUserId(userId).orElseThrow().getFriends().forEach(
                (friend) ->
                    messagingTemplate.convertAndSendToUser(
                            friend.getEmail(), "/queue/presence", Map.of("userId", userId, "online", false)
                    )
            );
        }
    }
}
