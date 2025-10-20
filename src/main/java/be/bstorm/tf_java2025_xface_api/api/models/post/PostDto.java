package be.bstorm.tf_java2025_xface_api.api.models.post;

import be.bstorm.tf_java2025_xface_api.api.models.user.UserPostDto;
import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile;

import java.time.LocalDateTime;
import java.util.Set;

public record PostDto(
        Long id,
        String title,
        String content,
        UserPostDto owner,
        Set<String> images,
        Set<String> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static PostDto fromEntity(Post p){
        return new PostDto(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                UserPostDto.fromEntity(p.getOwner()),
                p.getImages(),
                p.getTags(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }

    public static PostDto fromUserProfile(UserProfile p) {
        return new PostDto(
                p.post().getId(),
                p.post().getTitle(),
                p.post().getContent(),
                new UserPostDto(p.id(),p.firstName(),p.lastName(),p.image()),
                p.post().getImages(),
                p.post().getTags(),
                p.post().getCreatedAt(),
                p.post().getUpdatedAt()
        );
    }
}
