package be.bstorm.tf_java2025_xface_api.bll.services;

import be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<UserProfile> findUserProfile(
            UUID searchedUserId,
            UUID currentUserId,
            Pageable pageable
    );
    void addFriend(UUID userToAddId, UUID currentUserId);
    void removeFriend(UUID userToAddId, UUID currentUserId);
}
