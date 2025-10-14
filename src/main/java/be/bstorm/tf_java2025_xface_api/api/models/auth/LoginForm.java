package be.bstorm.tf_java2025_xface_api.api.models.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
