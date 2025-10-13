package be.bstorm.tf_java2025_xface_api.il.utils;

import be.bstorm.tf_java2025_xface_api.dl.entities.User;
import be.bstorm.tf_java2025_xface_api.dl.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final JwtBuilder builder;
    private final JwtParser parser;

    public JwtUtil() {
        String secret = "Kawabungaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        builder = Jwts.builder().signWith(secretKey);
        parser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateToken(User user) {

        int expireAt = 86400000;
        return builder
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("roles", user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() * expireAt))
                .compact();
    }

    public Claims getClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public List<UserRole> getRoles(String token) {
        List<?> rawRoles = getClaims(token).get("roles", List.class);
        return rawRoles.stream()
                .map(r -> UserRole.valueOf(r.toString()))
                .toList();
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);

        Date now  = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
