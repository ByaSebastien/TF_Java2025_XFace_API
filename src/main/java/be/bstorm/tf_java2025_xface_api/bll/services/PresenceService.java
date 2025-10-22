package be.bstorm.tf_java2025_xface_api.bll.services;


import java.util.Set;
import java.util.UUID;

public interface PresenceService {

    void register(UUID userId, String sessionId);

    void unregister(UUID userId, String sessionId);

    boolean isOnline(UUID userId);

    Set<UUID> getOnlineUsers();
}
