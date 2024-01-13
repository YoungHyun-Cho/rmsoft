package com.example.rmsoft.global.exception.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
public class FieldError implements Error {

    private String field;
    private Object rejectedValue;
    private String reason;

    private FieldError(String field, Object rejectedValue, String reason) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    public static List<Error> of(BindingResult bindingResult) {

        final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

        return Error.from(
                fieldErrors,
                fieldError -> new FieldError(
                        fieldError.getField(),
                        fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString(),
                        fieldError.getDefaultMessage()
                )
        );
    }
}
