package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserAccountService extends UserDetailsService {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
