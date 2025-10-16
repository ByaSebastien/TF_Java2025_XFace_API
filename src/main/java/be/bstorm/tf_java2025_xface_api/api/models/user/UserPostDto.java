package be.bstorm.tf_java2025_xface_api.api.models.user;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;

import java.util.UUID;

public record UserPostDto(
        UUID id,
        String firstName,
        String lastName,
        String image
) {

    public static UserPostDto fromEntity(User u){
        return new UserPostDto(u.getId(), u.getFirstName(), u.getLastName(), u.getImage());
    }
}
