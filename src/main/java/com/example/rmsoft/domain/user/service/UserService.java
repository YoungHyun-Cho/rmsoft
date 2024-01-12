package com.example.rmsoft.domain.user.service;

import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        return findVerifiedUser(userRepository.findByEmail(email));
    }

    public User findUser(Long userId) {

        return findVerifiedUser(userRepository.findById(userId));
    }

    private User findVerifiedUser(Optional<User> optionalUser) {

        return optionalUser.orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
