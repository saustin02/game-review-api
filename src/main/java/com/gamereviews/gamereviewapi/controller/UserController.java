package com.gamereviews.gamereviewapi.controller;


import com.gamereviews.gamereviewapi.entity.User;
import com.gamereviews.gamereviewapi.service.UserService;
import com.gamereviews.gamereviewapi.dto.RegisterRequest;
import com.gamereviews.gamereviewapi.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest register) {
        User registerUser = userService.registerUser(register.getUsername(), register.getEmail(), register.getPassword());
        return registerUser;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest login) {
        User loginUser = userService.loginUser(login.getUsername(), login.getPassword());
        return loginUser;
    }


}
