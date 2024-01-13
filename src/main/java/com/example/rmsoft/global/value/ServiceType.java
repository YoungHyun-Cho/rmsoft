package com.example.rmsoft.global.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public enum ServiceType {

    BASIC("BASIC"),
    STANDARD("STANDARD"),
    PREMIUM("PREMIUM");

    @Getter
    private String type;

    ServiceType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static ServiceType fromString(String value) {

        for (ServiceType serviceType : ServiceType.values()) {
            if (serviceType.type.equalsIgnoreCase(value)) return serviceType;
        }
        throw new IllegalArgumentException("Invalid PartyState"); // 추후 예외 처리 필요
    }
}
