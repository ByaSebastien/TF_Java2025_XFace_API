package be.bstorm.tf_java2025_xface_api.api.models.chat;

import java.util.UUID;

public record MessageForm(
    UUID receiverId,
    String content
) {
}
