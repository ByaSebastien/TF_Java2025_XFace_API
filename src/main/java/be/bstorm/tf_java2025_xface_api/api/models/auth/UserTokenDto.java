package be.bstorm.tf_java2025_xface_api.api.models.auth;

public record UserTokenDto(
        UserSessionDto user,
        String token
) {
}
