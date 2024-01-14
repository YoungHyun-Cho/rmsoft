package com.example.rmsoft.domain.auth.service;

import com.example.rmsoft.domain.auth.jwt.JwtTokenizer;
import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenizer jwtTokenizer;
    private final UserService userService;

    public Map<String, String> reIssueTokens(String refreshToken) throws ExpiredJwtException, MalformedJwtException {

        Map<String, String> tokenMap = new HashMap<>();

        Map<String, Object> claims = jwtTokenizer.getClaims(refreshToken).getBody();

        User user = userService.findUser((String) claims.get("sub"));
        tokenMap.put("accessToken", jwtTokenizer.delegateAccessToken(user));
        tokenMap.put("refreshToken", jwtTokenizer.delegateRefreshToken(user));

        return tokenMap;
    }
}
