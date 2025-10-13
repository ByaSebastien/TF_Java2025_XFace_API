package be.bstorm.tf_java2025_xface_api.bll.services.security.impls;

import be.bstorm.tf_java2025_xface_api.bll.services.security.AuthService;
import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
