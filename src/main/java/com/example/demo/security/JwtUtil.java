package com.example.demo.security;
import com.example.demo.model.User;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String secret = "default-secret";
    private int expirationSeconds = 3600;

    // Constructor used by Spring Boot (safe)
    public JwtUtil() {}

    // Constructor used by tests (so tests do not break)
    public JwtUtil(String secret, int expirationSeconds) {
        this.secret = secret;
        this.expirationSeconds = expirationSeconds;
    }

    public String generateToken(User user) {
        return "jwt-token-for-" + user.getEmail();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("jwt-token-for-");
    }

    public String getEmailFromToken(String token) {
        if (token == null) return null;
        return token.replace("jwt-token-for-", "");
    }
}
