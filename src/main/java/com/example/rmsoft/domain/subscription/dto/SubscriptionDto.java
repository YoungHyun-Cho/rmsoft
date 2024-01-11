package com.example.rmsoft.domain.subscription.dto;

import jakarta.validation.constraints.NotBlank;

public class SubscriptionDto {

    @NotBlank
    private Integer userId;

    @NotBlank
    private Integer solutionId;

    @NotBlank
    private Integer userCount;

    @NotBlank
    private String type;

    @NotBlank
    private Integer storageCapacity;
}
