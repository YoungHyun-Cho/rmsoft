package com.example.rmsoft.domain.subscription.dto;

import com.example.rmsoft.global.value.OperatingSystem;
import com.example.rmsoft.global.value.ServiceType;
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
public class SubscriptionRequestDto {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long solutionId;

    @NotBlank
    private Integer userCount;

    @NotBlank
    private ServiceType serviceType;

    @NotBlank
    private LocalDateTime expiration;

    @NotBlank
    private Integer storageCapacity;

    @NotBlank
    private OperatingSystem os;
}
