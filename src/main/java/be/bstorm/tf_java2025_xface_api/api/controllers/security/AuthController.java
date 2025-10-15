package be.bstorm.tf_java2025_xface_api.api.controllers.security;

import be.bstorm.tf_java2025_xface_api.api.models.auth.LoginForm;
import be.bstorm.tf_java2025_xface_api.api.models.auth.RegisterForm;
import be.bstorm.tf_java2025_xface_api.api.models.auth.UserSessionDto;
import be.bstorm.tf_java2025_xface_api.api.models.auth.UserTokenDto;
import be.bstorm.tf_java2025_xface_api.bll.services.security.AuthService;
import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.il.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestPart("form") RegisterForm form,
            @RequestPart(name = "image", required = false) MultipartFile image
            ) {

        authService.register(form.ToEntity(), image);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(
            @Valid @RequestBody LoginForm form
    ) {
        User user = authService.login(form.email(), form.password());

        UserSessionDto dto = UserSessionDto.fromEntity(user);
        String token = jwtUtil.generateToken(user);
        UserTokenDto userToken = new UserTokenDto(dto, token);

        return ResponseEntity.ok(userToken);
    }
}
