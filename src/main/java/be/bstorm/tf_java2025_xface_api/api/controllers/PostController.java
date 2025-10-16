package be.bstorm.tf_java2025_xface_api.api.controllers;

import be.bstorm.tf_java2025_xface_api.api.models.common.PageDto;
import be.bstorm.tf_java2025_xface_api.api.models.post.PostForm;
import be.bstorm.tf_java2025_xface_api.api.models.post.PostIndexDto;
import be.bstorm.tf_java2025_xface_api.bll.services.PostService;
import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<PageDto<PostIndexDto>> getPosts(
        @RequestParam(name = "page",defaultValue = "0", required = false) int page,
        @RequestParam(name = "size",defaultValue = "10", required = false) int size,
        @RequestParam(name = "sort1",defaultValue = "createdAt", required = false) String sort1,
        @RequestParam(name = "sort2",defaultValue = "updatedAt", required = false) String sort2
    ){
        PageRequest pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,sort1, sort2));

        Page<Post> result = postService.findAllPosts(pageable);

        PageDto<PostIndexDto> dto = new PageDto<>(
                result.getContent().stream().map(PostIndexDto::fromEntity).toList(),
                result.getNumber(),
                result.getTotalPages(),
                result.getNumberOfElements()
        );

        return ResponseEntity.ok(dto);
    }

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
