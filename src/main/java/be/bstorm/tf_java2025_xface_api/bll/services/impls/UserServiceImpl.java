package be.bstorm.tf_java2025_xface_api.bll.services.impls;

import be.bstorm.tf_java2025_xface_api.bll.services.UserService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.projections.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserProfile> findUserProfile(UUID searchedUserId, UUID currentUserId, Pageable pageable) {
        return userRepository.findUserProfile(searchedUserId, currentUserId, pageable);
    }

    @Override
    public void addFriend(UUID userToAddId, UUID currentUserId) {
        User userToAdd = userRepository.findById(userToAddId).orElseThrow();
        User currentUser = userRepository.findById(currentUserId).orElseThrow();

        if(currentUser.getFriends().contains(userToAdd)){
            throw new RuntimeException("User is already friend");
        }

        currentUser.addFriend(userToAdd);
        userToAdd.addFriend(currentUser);

        userRepository.save(currentUser);
    }

    @Override
    public void removeFriend(UUID userToRemoveId, UUID currentUserId) {
        User userToRemove = userRepository.findById(userToRemoveId).orElseThrow();
        User currentUser = userRepository.findById(currentUserId).orElseThrow();

        if(!currentUser.getFriends().contains(userToRemove)){
            throw new RuntimeException("User is not friend");
        }

        currentUser.removeFriend(userToRemove);
        userToRemove.removeFriend(currentUser);

        userRepository.save(currentUser);
    }
}
