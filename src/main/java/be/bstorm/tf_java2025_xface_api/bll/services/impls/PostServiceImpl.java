package be.bstorm.tf_java2025_xface_api.bll.services.impls;

import be.bstorm.tf_java2025_xface_api.bll.services.PostService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.PostRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import be.bstorm.tf_java2025_xface_api.il.utils.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageUtil imageUtil;


    @Override
    @Transactional
    public void save(Post post, MultipartFile[] images) {

        if (images != null && images.length > 0) {
            Set<String> filenames = Arrays.stream(images)
                    .map(imageUtil::save)
                    .collect(Collectors.toSet());
            post.setImages(filenames);
        }

        postRepository.save(post);
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findPostsWithOwner(pageable);
    }
}
