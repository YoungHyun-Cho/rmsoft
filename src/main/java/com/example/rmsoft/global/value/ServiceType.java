package com.example.rmsoft.global.value;

import com.example.rmsoft.global.exception.BusinessLogicException;
import com.example.rmsoft.global.exception.ExceptionCode;
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
        throw new BusinessLogicException(ExceptionCode.NOT_SUPPORTED_SERVICE_TYPE);
    }
}
