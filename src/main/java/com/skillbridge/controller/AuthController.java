package com.skillbridge.controller;
import com.skillbridge.model.User;
import com.skillbridge.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.skillbridge.dto.LoginRequest;

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
    public User login(@RequestBody LoginRequest request) {

        return userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

    }
}
