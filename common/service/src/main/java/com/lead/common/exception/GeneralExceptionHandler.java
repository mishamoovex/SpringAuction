package com.lead.common.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(x ->
                errors.put(((FieldError) x).getField(), x.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(BadStateException.class)
    public ResponseEntity<?> handleBadStateException(BadStateException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errors);
    }

    @ExceptionHandler(BadRequestDataException.class)
    public ResponseEntity<?> handleBadRequestDataException(BadRequestDataException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errors);
    }
}
