package com.lead.service.auth.exception;

import com.lead.common.exception.GeneralExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class AuthenticationExceptionHandler extends GeneralExceptionHandler {

    @ExceptionHandler(WrongCredentialsException.class)
    public final ResponseEntity<?> wrongCredentials(WrongCredentialsException exception) {
        var errors = new HashMap<String, String>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }
}
