package be.bstorm.tf_java2025_xface_api.bll.services.security;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);
}
