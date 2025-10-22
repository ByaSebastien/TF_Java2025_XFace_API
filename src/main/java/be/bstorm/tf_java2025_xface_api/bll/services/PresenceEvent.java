package be.bstorm.tf_java2025_xface_api.bll.services;

import java.util.UUID;

public record PresenceEvent(
        UUID userId,
        boolean online
) {
}
