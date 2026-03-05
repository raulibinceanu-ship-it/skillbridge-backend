package com.skillbridge.controller;

import com.skillbridge.dto.LoginResponse;
import com.skillbridge.model.User;
import com.skillbridge.security.JwtUtil;
import com.skillbridge.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User request) {

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        String token = JwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}