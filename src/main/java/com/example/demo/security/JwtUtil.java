package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
        public JwtUtil(String secret, int expirationSeconds) {
            // ignore these inputs, your project doesn’t use real JWT anyway
        }

    public String generateToken(String email) {
        // Simple dummy token (enough for compilation & demo)
        return "jwt-token-for-" + email;
    }

    public boolean validateToken(String token) {
        // Dummy validation
        return token != null && token.startsWith("jwt-token-for-");
    }

    public String getEmailFromToken(String token) {
        if (token == null) return null;
        return token.replace("jwt-token-for-", "");
    }
}
