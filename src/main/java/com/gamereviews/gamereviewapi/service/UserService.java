package com.gamereviews.gamereviewapi.service;

import com.gamereviews.gamereviewapi.entity.User;
import com.gamereviews.gamereviewapi.exception.ResourceNotFoundException;
import com.gamereviews.gamereviewapi.exception.ValidationException;
import com.gamereviews.gamereviewapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String email, String password) {
        // Validation
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (userRepository.findByUsername(username) != null) {
            throw new ValidationException("Username already taken");
        }
        if (!isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
        if (userRepository.findByEmail(email) != null) {
            throw new ValidationException("Email already registered");
        }
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters");
        }

        // Create and save user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ValidationException("Invalid password");
        }
        return user;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}