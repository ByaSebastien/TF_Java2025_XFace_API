package be.bstorm.tf_java2025_xface_api.bll.services;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceServiceImpl implements PresenceService {

    private final ConcurrentHashMap<UUID, Set<String>> online = new ConcurrentHashMap<>();

    @Override
    public void register(UUID userId, String sessionId){
        online.compute(userId, (k,v) -> {
            if (v == null) v = ConcurrentHashMap.newKeySet();
            v.add(sessionId);
            return v;
        });
    }

    @Override
    public void unregister(UUID userId, String sessionId){
        online.computeIfPresent(userId, (k,v) -> {
            v.remove(sessionId);
            return v.isEmpty() ? null : v;
        });
    }

    @Override
    public boolean isOnline(UUID userId){
        return online.containsKey(userId);
    }

    @Override
    public Set<UUID> getOnlineUsers(){
        return online.keySet();
    }
}
