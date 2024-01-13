package com.example.rmsoft.global.exception.error;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ConstraintViolationError implements Error {

    private String field;
    private Object rejectedValue;
    private String reason;

    private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
        this.field = propertyPath;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    public static List<Error> of(Set<ConstraintViolation<?>> constraintViolations) {

        return Error.from(
                constraintViolations,
                constraintViolation -> new ConstraintViolationError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage()
                )
        );
    }
}
