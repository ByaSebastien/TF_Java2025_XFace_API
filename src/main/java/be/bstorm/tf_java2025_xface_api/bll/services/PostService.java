package be.bstorm.tf_java2025_xface_api.bll.services;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    void save(Post post, MultipartFile[] images);

    Page<Post> findAllPosts(Pageable pageable);
}
