package com.example.rmsoft.domain.subscription.dto;

import com.example.rmsoft.domain.solution.dto.SolutionDto;
import com.example.rmsoft.global.value.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponseDto {

    private Long id;

    private SolutionDto solution;

    private Integer userCount;

    private ServiceType serviceType;

    private LocalDateTime expiration;

    private Integer storageCapacity;

    private Integer storageUsage;

    private Integer totalPrice;
}
