package com.example.rmsoft.domain.user.controller;

import com.example.rmsoft.domain.user.dto.UserDto;
import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.mapper.UserMapper;
import com.example.rmsoft.domain.user.service.UserService;
import com.example.rmsoft.global.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserDto userDto) {

        User user = userService.createUser(userMapper.userDtoToUser(userDto));

        URI uri = Utility.makeURI("/users/{user-id}", user.getId());

        return ResponseEntity.created(uri).build();
    }
}
