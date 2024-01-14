package com.example.rmsoft.domain.subscription.dto;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPatchDto {

    @Future(message = "유효한 미래의 날짜를 입력해주세요.")
    private LocalDateTime expiration;
}
