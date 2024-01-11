package com.example.rmsoft.global.util;

import org.springframework.http.ResponseCookie;

public class Utility {

    public static ResponseCookie createCookie(String name, String value, Integer minutes) {

        return ResponseCookie.from(name, value)
                .domain("localhost")
                .path("/")
                .sameSite("None")
                .maxAge(minutes * 60)
                .build();
    }

}
