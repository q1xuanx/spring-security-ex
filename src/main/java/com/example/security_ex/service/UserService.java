package com.example.security_ex.service;

import com.example.security_ex.dto.response.ApiResponse;
import com.example.security_ex.model.UserPrinciple;
import com.example.security_ex.model.Users;
import com.example.security_ex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("User not found");
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(user);
    }

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
}
