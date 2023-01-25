package br.ufsm.elc1071.startthecount.rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class ErrorResponse {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String description;

    private final HttpStatus status;

    private final Set<Error> errors;

    @Setter(value = AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    public ErrorResponse(Set<Error> errors, HttpStatus status) {
        this.errors = errors;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(Set<Error> errors, String description, HttpStatus status) {
        this(errors, status);
        this.description = description;
    }

    public ErrorResponse(Error error, String description, HttpStatus status) {
        this(Set.of(error), description, status);
    }
    public ErrorResponse(Error error, HttpStatus status) {
        this(Set.of(error), status);
    }
}
