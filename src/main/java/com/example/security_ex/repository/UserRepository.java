package com.example.security_ex.repository;

import com.example.security_ex.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByUsernameAndPassword(String username, String password);
}
