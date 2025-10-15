package be.bstorm.tf_java2025_xface_api.bll.services;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    void save(Post post, MultipartFile[] images);
}
