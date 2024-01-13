package com.example.rmsoft.domain.user.service;

import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.repository.UserRepository;
import com.example.rmsoft.global.exception.BusinessLogicException;
import com.example.rmsoft.global.exception.ExceptionCode;
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

        verifyExistEmail(user.getEmail());

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public User findUser(Long userId) {

        return findVerifiedUser(userRepository.findById(userId));
    }

    private User findVerifiedUser(Optional<User> optionalUser) {

        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    private void verifyExistEmail(String email) {

        if (userRepository.findByEmail(email).isPresent()) throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }
}
