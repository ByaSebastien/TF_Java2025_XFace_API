package be.bstorm.tf_java2025_xface_api.dal.repositories;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query("""
    select new be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile(
        u.id, u.firstName, u.lastName, u.birthDate, u.image,
        (select count(f1) from User u1 join u1.friends f1 where u1 = u),
        (select count(f2) > 0
         from User cu join cu.friends f2
         where cu.id = :currentUserId and f2 = u),
        (select count(f3)
         from User cu join cu.friends f3
         where cu.id = :currentUserId and f3 member of u.friends),
        p
    )
    from Post p
    join p.owner u
    where u.id = :searchedUserId
    order by p.createdAt desc
    """)
    Page<UserProfile> findUserProfile(
            UUID searchedUserId,
            UUID currentUserId,
            Pageable pageable
    );

    @Query("select u from User u join fetch u.friends where u.id = :userId")
    Optional<User> findUserWithFriendsByUserId(UUID userId);
}
