package com.example.rmsoft.domain.subscription.dto;

import com.example.rmsoft.global.value.OperatingSystem;
import com.example.rmsoft.global.value.ServiceType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPostDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long solutionId;

    @Min(value = 1, message = "최소 1 이상의 값을 입력해야 합니다.")
    private Integer userCount;

    @Future(message = "유효한 미래의 날짜를 입력해주세요.")
    private LocalDateTime expiration;

    @Range(min = 100, max = 10000, message = "100~10000TB 사이에서 용량을 선택해주세요.")
    private Integer storageCapacity;

    @NotNull
    private ServiceType serviceType;

    @NotNull
    private OperatingSystem os;
}
