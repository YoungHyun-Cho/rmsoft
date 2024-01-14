package com.example.rmsoft.domain.auth.controller;

import com.example.rmsoft.domain.auth.service.AuthService;
import com.example.rmsoft.global.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity refresh(@RequestHeader("Refresh") String refreshToken) {

        Map<String, String> tokenMap = authService.reIssueTokens(refreshToken);

        HttpHeaders headers = Utility.setCookie(
                tokenMap.get("accessToken"),
                tokenMap.get("refreshToken")
        );

        return ResponseEntity.ok().headers(headers).build();
    }
}
