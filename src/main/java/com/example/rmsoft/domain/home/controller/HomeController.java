package com.example.rmsoft.domain.home.controller;

import com.example.rmsoft.global.config.DummyDataConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    private final DummyDataConfig dummyDataConfig;

    @GetMapping
    public ResponseEntity getHome(@AuthenticationPrincipal UserDetails userDetails) {

        dummyDataConfig.initiate();

        Boolean isLoggedIn = userDetails != null;

        return new ResponseEntity("Hello RM Soft!\nLogin State : " + isLoggedIn, HttpStatus.OK);
    }
}
