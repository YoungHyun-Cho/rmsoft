package com.example.rmsoft.domain.subscription.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private Long solutionId;

    @NotBlank
    private Integer userCount;

    @NotBlank
    private String type;

    @NotBlank
    private LocalDateTime expiration;

    @NotBlank
    private Integer storageCapacity;

    private Integer storageUsage;
}
