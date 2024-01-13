package com.example.rmsoft.global.util;

import com.example.rmsoft.domain.auth.userdetails.UserDetail;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class Utility {

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

}
