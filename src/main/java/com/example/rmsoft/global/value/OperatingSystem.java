package com.example.rmsoft.global.value;

import lombok.Getter;

public enum OperatingSystem {

    WINDOWS("WINDOWS"),
    MAC("MAC"),
    LINUX("LINUX");

    @Getter
    private String name;

    OperatingSystem(String name) {
        this.name = name;
    }
}
