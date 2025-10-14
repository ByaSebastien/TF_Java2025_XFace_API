package be.bstorm.tf_java2025_xface_api.bll.services.security;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);

    User login(String email, String password);
}
