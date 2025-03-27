package com.example.security_ex.controller;


import com.example.security_ex.model.Users;
import com.example.security_ex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody Users user) {
        userRepository.save(user);
        return "Sign up success with username: " + user.getUsername();
    }
}
