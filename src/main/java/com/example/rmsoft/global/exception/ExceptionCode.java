package com.example.rmsoft.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_EXISTS(409, "이미 존재하는 사용자입니다."),
    SOLUTION_NOT_FOUND(404, "솔루션을 찾을 수 없습니다."),
    SUBSCRIPTION_NOT_FOUND(404, "구독 정보를 찾을 수 없습니다."),
    SUBSCRIPTION_EXISTS(409, "해당 솔루션을 이미 구독하고 있습니다."),
    NOT_SUPPORTED_OS(400, "해당 솔루션은 지원하지 않는 OS입니다."),
    NOT_SUPPORTED_SERVICE_TYPE(400, "존재하지 않는 서비스 타입입니다.");

    @Getter
    private Integer status;

    @Getter
    private String message;

    ExceptionCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
