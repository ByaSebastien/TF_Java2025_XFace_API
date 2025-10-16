package be.bstorm.tf_java2025_xface_api.dal.repositories;

import be.bstorm.tf_java2025_xface_api.dl.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @EntityGraph(attributePaths = "owner")
    @Query("select p from Post p")
    Page<Post> findPostsWithOwner(Pageable pageable);
}
