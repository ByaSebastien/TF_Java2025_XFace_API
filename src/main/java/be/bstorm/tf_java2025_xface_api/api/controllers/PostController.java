package be.bstorm.tf_java2025_xface_api.api.controllers;

import be.bstorm.tf_java2025_xface_api.api.models.post.PostForm;
import be.bstorm.tf_java2025_xface_api.bll.services.PostService;
import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> createPost(
            @Valid @RequestPart("form") PostForm form,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @AuthenticationPrincipal User user
    ) {

        Post post = form.toEntity();
        post.setOwner(user);

        postService.save(post, images);

        return ResponseEntity.noContent().build();
    }
}
