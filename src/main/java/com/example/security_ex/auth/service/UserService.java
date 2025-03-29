package com.example.security_ex.auth.service;


import com.example.security_ex.auth.dto.request.LoginRequest;
import com.example.security_ex.auth.dto.response.ApiResponse;
import com.example.security_ex.auth.model.Users;
import com.example.security_ex.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    //Print log of the application
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    //Init service and repository
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final JwtService jwtService;

    public ApiResponse<Users> register(Users user, String urlRequest) {
        Users checkUserValid = userRepository.findByUsername(user.getUsername());
        if (checkUserValid != null) {
            log.info("User name already exists");
            return new ApiResponse<>(400, "Username had exist", new Users(), urlRequest);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ApiResponse<>(200, "Create success", user, urlRequest);
    }

    public ApiResponse<List<Users>> getAllUser(String urlRequest) {
        return new ApiResponse<>(200, "Get all users", userRepository.findAll(), urlRequest);
    }

    public ApiResponse<Object> verify(LoginRequest loginRequest, String urlRequest) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (auth.isAuthenticated()) {
            String token = jwtService.generateJwtToken(loginRequest);
            return new ApiResponse<>(200, "Login success", token , urlRequest);
        }
        return new ApiResponse<>(400, "Login failed", "Bad", urlRequest);
    }

}
