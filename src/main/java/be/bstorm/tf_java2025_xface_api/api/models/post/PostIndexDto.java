package be.bstorm.tf_java2025_xface_api.api.models.post;

import be.bstorm.tf_java2025_xface_api.api.models.user.UserPostDto;
import be.bstorm.tf_java2025_xface_api.dl.entities.Post;

import java.time.LocalDateTime;
import java.util.Set;

public record PostIndexDto(
        Long id,
        String title,
        String content,
        UserPostDto owner,
        Set<String> images,
        Set<String> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static PostIndexDto fromEntity(Post p){
        return new PostIndexDto(
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
}
