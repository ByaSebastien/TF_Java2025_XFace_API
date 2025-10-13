package be.bstorm.tf_java2025_xface_api.il.configs;

import be.bstorm.tf_java2025_xface_api.bll.services.security.AuthService;
import be.bstorm.tf_java2025_xface_api.il.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            if(jwtUtil.isValid(token)) {

                String username = jwtUtil.getEmail(token);

                UserDetails user = authService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(upt);
            }
        }

        filterChain.doFilter(request, response);
    }
}
