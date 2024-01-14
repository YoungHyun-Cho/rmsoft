package com.example.rmsoft.global.value;

import com.example.rmsoft.global.exception.BusinessLogicException;
import com.example.rmsoft.global.exception.ExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public static OperatingSystem fromString(String value) {

        for (OperatingSystem operatingSystem : OperatingSystem.values()) {
            if (operatingSystem.name.equalsIgnoreCase(value)) return operatingSystem;
        }
        throw new BusinessLogicException(ExceptionCode.NOT_SUPPORTED_OS);
    }
}
