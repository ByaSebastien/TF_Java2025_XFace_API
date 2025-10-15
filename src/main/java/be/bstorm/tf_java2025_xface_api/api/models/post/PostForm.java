package be.bstorm.tf_java2025_xface_api.api.models.post;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record PostForm(
        @NotBlank @Size(max = 100)
        String title,
        @NotBlank @Size(max = 500)
        String content,
        Set<String> tags
) {
    public Post toEntity() {
        return new Post(title,content,tags);
    }
}
