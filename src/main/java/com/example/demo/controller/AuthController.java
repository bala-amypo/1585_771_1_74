package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {

        User savedUser = userService.register(user);

        AuthResponse dto = new AuthResponse();
        dto.id = savedUser.getId();
        dto.email = savedUser.getEmail();
        dto.role = savedUser.getRole();

        return dto;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

        User found = userService.findByEmail(user.getEmail());

        AuthResponse dto = new AuthResponse();
        dto.id = found.getId();
        dto.email = found.getEmail();
        dto.role = found.getRole();

        return dto;
    }
}
