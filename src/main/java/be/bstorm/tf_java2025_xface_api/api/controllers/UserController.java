package be.bstorm.tf_java2025_xface_api.api.controllers;

import be.bstorm.tf_java2025_xface_api.api.models.user.UserProfileDto;
import be.bstorm.tf_java2025_xface_api.bll.services.UserService;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(
            @PathVariable(name = "id") UUID searchedUserId,
            @AuthenticationPrincipal User currentUser
    ) {

        UserProfileDto profile = UserProfileDto.fromUserProfile(userService.findUserProfile(
                searchedUserId,
                currentUser.getId(),
                PageRequest.of(0, 10)
        ));

        return ResponseEntity.ok(profile);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addFriend/{id}")
    public ResponseEntity<Void> addFriend(
            @PathVariable(name = "id") UUID userToAddId,
            @AuthenticationPrincipal User currentUser
    ) {
        userService.addFriend(userToAddId, currentUser.getId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/removeFriend/{id}")
    public ResponseEntity<Void> removeFriend(
            @PathVariable(name = "id") UUID userToRemoveId,
            @AuthenticationPrincipal User currentUser
    ) {
        userService.removeFriend(userToRemoveId, currentUser.getId());
        return ResponseEntity.ok().build();
    }
}
