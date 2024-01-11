package com.example.rmsoft.domain.auth.userdetails;

import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User foundUser = optionalUser.orElseThrow(() -> new RuntimeException("User Not Found"));
        return new UserDetail(foundUser);
    }
}
