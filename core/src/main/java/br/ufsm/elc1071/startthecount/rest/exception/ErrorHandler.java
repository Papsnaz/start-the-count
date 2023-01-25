package br.ufsm.elc1071.startthecount.rest.exception;

import br.ufsm.elc1071.startthecount.core.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    private ResponseEntity<Object> handleError(String message, HttpStatus status) {
        return new ResponseEntity<>(
            new ErrorResponse(
                new Error(message),
                status
            ),
            status
        );
    }

    @ExceptionHandler(value = {
        AssinaturasDivergentesException.class,
        FalhaRequisicaoWebException.class,
        FalhaVerificacaoAssinaturaException.class,
        HashsDivergentesException.class,
        QRCodeFaltanteException.class,
        VersoesChaveAssinaturaDivergentesException.class,
        CamposFaltantesException.class,
        UsuarioNaoAutenticadoException.class,
        EstruturaIncorretaPayloadQRCodeBoletimUrnaException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBoletimUrnaQRCodeException(
        Exception exception
    ) {
        return this.handleError(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = DadosFaltantesException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDadosFaltantesException(
        DadosFaltantesException dadosFaltantesException
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            dadosFaltantesException
                .getDadosFaltantes()
                .entrySet()
                .stream()
                .map(dadoFaltante -> new ValidationError(dadoFaltante.getKey(), dadoFaltante.getValue()))
                .collect(Collectors.toSet()),
            dadosFaltantesException.getMessage(),
            status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = PSQLException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handlePSQLExceptionException(
            PSQLException psqlException
    ) {
        return this.handleError(
            psqlException.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = EntidadeJaExisteException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleEntityAlreadyExistsException(
        EntidadeJaExisteException entidadeJaExisteException
    ) {
        return this.handleError(
            entidadeJaExisteException.getMessage(),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = EntidadeNaoEncontradaException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntidadeNaoEncontradaException entidadeNaoEncontradaException
    ) {
        return this.handleError(
            entidadeNaoEncontradaException.getMessage(),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintValidationException(
        ConstraintViolationException constraintViolationException
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            constraintViolationException
                .getConstraintViolations()
                .stream()
                .map(ValidationError::new)
                .collect(Collectors.toSet()),
            status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .collect(Collectors.toSet()),
            status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBindException(BindException bindException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            bindException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .collect(Collectors.toSet()),
            status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ConversionFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConversionFailed(
        ConversionFailedException conversionFailedException
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            new ValidationError(
                Objects.requireNonNull(conversionFailedException.getValue()).toString(),
                conversionFailedException.getCause().getMessage()
            ),
            status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException httpMessageNotReadableException
    ) {
        return this.handleError(
            httpMessageNotReadableException.getMessage(),
            HttpStatus.BAD_REQUEST
        );
    }
}
