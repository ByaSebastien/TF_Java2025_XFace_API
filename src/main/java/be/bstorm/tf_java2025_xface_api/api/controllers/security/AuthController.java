package be.bstorm.tf_java2025_xface_api.api.controllers.security;

import be.bstorm.tf_java2025_xface_api.api.models.auth.RegisterForm;
import be.bstorm.tf_java2025_xface_api.bll.services.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterForm form
            ) {

        authService.register(form.ToEntity());

        return ResponseEntity.accepted().build();
    }
}
