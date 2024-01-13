package com.example.rmsoft.global.exception;

import com.example.rmsoft.global.exception.error.ConstraintViolationError;
import com.example.rmsoft.global.exception.error.Error;
import com.example.rmsoft.global.exception.error.FieldError;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;

@Getter
public class ErrorResponse {

    private Integer status;
    private String message;
    private List<Error> errors;

    private ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private ErrorResponse(List<Error> errors) {
        this.errors = errors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult));
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(ConstraintViolationError.of(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }
}
