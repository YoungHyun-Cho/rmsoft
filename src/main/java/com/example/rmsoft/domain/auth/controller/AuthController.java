package com.example.rmsoft.domain.auth.controller;

import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.mapper.UserMapper;
import com.example.rmsoft.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity logIn(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.findUser(userDetails.getUsername());

        // 404 Not Found 발생 -> Path가 /로 인식되는 문제 해결 필요

        return new ResponseEntity(userMapper.userToUserDto(user), HttpStatus.OK);
    }
}
