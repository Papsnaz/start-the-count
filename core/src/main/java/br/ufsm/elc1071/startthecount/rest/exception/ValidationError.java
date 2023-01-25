package br.ufsm.elc1071.startthecount.rest.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.ConstraintViolation;

import lombok.Getter;

import org.springframework.validation.FieldError;

@Getter
@JsonPropertyOrder(value = {"field", "message"})
public class ValidationError extends Error {

    private final String field;

    public ValidationError(String field, String message) {
        super(message);
        this.field = field;
    }

    public ValidationError(ConstraintViolation<?> constraintViolation) {
        this(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }

    public ValidationError(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
