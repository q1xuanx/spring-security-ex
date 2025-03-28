package com.example.security_ex.controller;


import com.example.security_ex.dto.response.ApiResponse;
import com.example.security_ex.model.Users;
import com.example.security_ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
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

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        ApiResponse<List<Users>> listUser = userService.getAllUser("/users");
        if (!listUser.getData().isEmpty()) {
            return ResponseEntity.ok(listUser);
        }
        return ResponseEntity.noContent().build();
    }
}
