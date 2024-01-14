package com.example.rmsoft.global.util;

import com.example.rmsoft.domain.auth.userdetails.UserDetail;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class Utility {

    private static Integer accessTokenExpirationMinutes;
    private static Integer refreshTokenExpirationMinutes;


    @Value("${jwt.access-token-expiration-minutes}")
    public void setAccessTokenExpirationMinutes(Integer accessTokenExpirationMinutes) {
        Utility.accessTokenExpirationMinutes = accessTokenExpirationMinutes;
    }

    @Value("${jwt.refresh-token-expiration-minutes}")
    public void setRefreshTokenExpirationMinutes(Integer refreshTokenExpirationMinutes) {
        Utility.refreshTokenExpirationMinutes = refreshTokenExpirationMinutes;
    }

    public static ResponseCookie createCookie(String name, String value, Integer minutes) {

        return ResponseCookie.from(name, value)
                .domain("localhost")
                .path("/")
                .sameSite("None")
                .maxAge(minutes * 60)
                .build();
    }

    public static Long getUserId(UserDetails userDetails) {

        return ((UserDetail) userDetails).getId();
    }

    public static URI makeURI(String path, Long value) {

        return UriComponentsBuilder
                        .newInstance()
                        .path(path)
                        .buildAndExpand(value)
                        .toUri();
    }

    public static HttpHeaders setCookie(String accessToken, String refreshToken) {

        HttpHeaders headers = new HttpHeaders();
        ResponseCookie accessTokenCookie = createCookie("access", accessToken, accessTokenExpirationMinutes);
        ResponseCookie refreshTokenCookie = createCookie("refresh", refreshToken, refreshTokenExpirationMinutes);
        headers.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return headers;
    }
}
