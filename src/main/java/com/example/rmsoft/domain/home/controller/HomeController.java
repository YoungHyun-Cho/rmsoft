package com.example.rmsoft.domain.home.controller;

import com.example.rmsoft.domain.home.dto.HomeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ResponseEntity getHome(@AuthenticationPrincipal UserDetails userDetails) {

        Boolean isLoggedIn = userDetails != null;

        return new ResponseEntity("Hello RM Soft!\nLogin State : " + isLoggedIn, HttpStatus.OK);
    }
}
