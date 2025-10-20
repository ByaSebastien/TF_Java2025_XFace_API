package be.bstorm.tf_java2025_xface_api.api.models.user;


import be.bstorm.tf_java2025_xface_api.api.models.common.PageDto;
import be.bstorm.tf_java2025_xface_api.api.models.post.PostDto;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserProfileDto(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String image,
        Long totalFriends,
        Boolean isFriend,
        Long commonFriends,
        PageDto<PostDto> postsPage
) {

    public static UserProfileDto fromUserProfile(Page<UserProfile> page) {

        UserProfile first = page.getContent().stream().findFirst().orElseThrow();

        List<PostDto> posts = page.getContent().stream()
                .map(PostDto::fromUserProfile)
                .toList();

        PageDto<PostDto> postsPage = new PageDto<>(
                posts,
                page.getNumber(),
                page.getTotalPages(),
                page.getNumberOfElements()
        );

        return new UserProfileDto(
                first.id(),
                first.firstName(),
                first.lastName(),
                first.birthDate(),
                first.image(),
                first.totalFriends(),
                first.isFriend(),
                first.commonFriends(),
                postsPage
        );
    }
}
