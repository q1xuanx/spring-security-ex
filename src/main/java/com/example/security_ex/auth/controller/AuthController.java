package com.example.security_ex.auth.controller;


import com.example.security_ex.auth.dto.request.LoginRequest;
import com.example.security_ex.auth.dto.response.ApiResponse;
import com.example.security_ex.auth.model.Users;
import com.example.security_ex.auth.service.UserDetailService;
import com.example.security_ex.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Users>> signUp(@RequestBody Users user) {
        ApiResponse<Users> response = userService.register(user, "/sign-up");
        if (response.getCode() == 400) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginRequest user) {
        ApiResponse<Object> response = userService.verify(user, "/login");
        if (response.getCode() == 400) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        ApiResponse<List<Users>> listUser = userService.getAllUser("/users");
        if (!listUser.getData().isEmpty()) {
            return ResponseEntity.ok(listUser);
        }
        return ResponseEntity.noContent().build();
    }
}
