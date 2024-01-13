package com.example.rmsoft.global.config;

import com.example.rmsoft.global.value.ServiceType;

import java.util.HashMap;

public class PriceConfig {

    private static final Integer pricePerOneTB = 10;

    public static Double multiplierByType(ServiceType serviceType) {

        switch (serviceType) {
            case BASIC: return 1.0;
            case STANDARD: return 1.2;
            case PREMIUM: return 1.5;
            default: throw new RuntimeException("Invalid Service Type");
        }
    }

    public static Integer multiplierByStorage(Integer storageCapacity) {

        return pricePerOneTB * storageCapacity;
    }
}
