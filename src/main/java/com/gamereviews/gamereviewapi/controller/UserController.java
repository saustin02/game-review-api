package com.gamereviews.gamereviewapi.controller;

import com.gamereviews.gamereviewapi.dto.UserResponse;
import com.gamereviews.gamereviewapi.entity.User;
import com.gamereviews.gamereviewapi.service.UserService;
import com.gamereviews.gamereviewapi.dto.RegisterRequest;
import com.gamereviews.gamereviewapi.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest register) {
        User registerUser = userService.registerUser(register.getUsername(), register.getEmail(), register.getPassword());
        return toUserResponse(registerUser);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest login) {
        User loginUser = userService.loginUser(login.getUsername(), login.getPassword());
        return toUserResponse(loginUser);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

}
