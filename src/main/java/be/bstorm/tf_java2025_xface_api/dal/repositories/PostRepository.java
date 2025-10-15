package be.bstorm.tf_java2025_xface_api.dal.repositories;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
