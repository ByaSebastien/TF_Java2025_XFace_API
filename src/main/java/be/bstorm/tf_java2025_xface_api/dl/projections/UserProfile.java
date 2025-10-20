package be.bstorm.tf_java2025_xface_api.dl.projections;

import java.time.LocalDate;
import java.util.UUID;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;

public record UserProfile(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String image,
        Long totalFriends,
        Boolean isFriend,
        Long commonFriends,
        Post post
) {}
