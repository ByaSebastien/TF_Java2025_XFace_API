package be.bstorm.tf_java2025_xface_api.api.models.auth;

import be.bstorm.tf_java2025_xface_api.api.validators.annotations.AgeMin;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterForm(
        @Email @NotBlank @Size(max = 150)
        String email,
        @NotBlank
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&=])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Le mot de passe doit contenir au moins 8 caractères, dont une majuscule, une minuscule, un chiffre et un caractère spécial."
        )
        String password,
        @NotBlank @Size(max = 123)
        String firstName,
        @NotBlank @Size(max = 80)
        String lastName,
        @NotNull @AgeMin(min = 16)
        LocalDate birthDate
) {

    public User ToEntity() {
        return new  User(email, password, firstName, lastName, birthDate);
    }
}
