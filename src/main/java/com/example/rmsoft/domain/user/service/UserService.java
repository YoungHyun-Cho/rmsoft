package com.example.rmsoft.domain.user.service;

import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public User findUser(String email) {

        return findVerifiedUser(email);
    }

    private User findVerifiedUser(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}