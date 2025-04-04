package com.natixis.ecommerce.controller;

import com.natixis.ecommerce.dto.request.UserRegistrationDto;
import com.natixis.ecommerce.model.User;
import com.natixis.ecommerce.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.natixis.ecommerce.model.Role.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        if (registrationDto.getRole().equals(ADMIN.name())) {
            user.setRole(ADMIN);
        }
        if (registrationDto.getRole().equals(USER.name())) {
            user.setRole(USER);
        }
       // Default role

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

}

