package be.bstorm.tf_java2025_xface_api.dal;

import be.bstorm.tf_java2025_xface_api.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class initializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count()==0){
            String password = passwordEncoder.encode("Test1234!");
            List<User> users = List.of(
                    new User(
                            UUID.randomUUID(),
                            "admin@test.be",
                            password,
                            "admin",
                            "admin",
                            LocalDate.of(1991,3,27),
                            Set.of(UserRole.ADMIN,UserRole.MODERATOR,UserRole.USER)
                    ),
                    new User(
                            UUID.randomUUID(),
                            "moderator@test.be",
                            password,
                            "moderator",
                            "moderator",
                            LocalDate.of(1991,3,27),
                            Set.of(UserRole.MODERATOR,UserRole.USER)
                    ),
                    new User(
                            UUID.randomUUID(),
                            "user@test.be",
                            password,
                            "user",
                            "user",
                            LocalDate.of(1991,3,27),
                            Set.of(UserRole.USER)
                    )
            );
            userRepository.saveAll(users);
        }
    }
}
