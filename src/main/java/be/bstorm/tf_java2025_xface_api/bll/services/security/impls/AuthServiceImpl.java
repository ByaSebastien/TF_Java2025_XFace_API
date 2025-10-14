package be.bstorm.tf_java2025_xface_api.bll.services.security.impls;

import be.bstorm.tf_java2025_xface_api.bll.services.security.AuthService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.enums.UserRole;
import be.bstorm.tf_java2025_xface_api.il.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageUtil imageUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public void register(User user, MultipartFile image) {

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UsernameNotFoundException("Email already in use");
        }

        if(!image.isEmpty()) {
            String fileName = imageUtil.save(image);
            user.setImage(fileName);
        }

        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(UserRole.USER));

        // Todo handle image

        userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email).orElseThrow();

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return user;
    }
}
