package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String secret = "default-secret";
    private int expirationSeconds = 3600;

    public JwtUtil() {}

    public JwtUtil(String secret, int expirationSeconds) {
        this.secret = secret;
        this.expirationSeconds = expirationSeconds;
    }

    /**
     * Expects a User object to generate the token.
     */
    public String generateToken(User user) {
        return "jwt-token-for-" + user.getEmail();
    }
     public String generateToken(String email) {
        return "jwt-token-for-" + email;
    }
    public boolean validateToken(String token) {
        return token != null && token.startsWith("jwt-token-for-");
    }

    public String getEmailFromToken(String token) {
        if (token == null) return null;
        // Fix: Use the parameter 'token', not 'jwtUtil'
        return token.replace("jwt-token-for-", "");
    }
}