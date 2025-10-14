package be.bstorm.tf_java2025_xface_api.api.models.auth;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.enums.UserRole;

import java.util.Set;
import java.util.UUID;

public record UserSessionDto(
        UUID id,
        String firstName,
        Set<UserRole> roles
) {

    public static UserSessionDto fromEntity(User u){
        return new UserSessionDto(u.getId(), u.getFirstName(), u.getRoles());
    }
}
