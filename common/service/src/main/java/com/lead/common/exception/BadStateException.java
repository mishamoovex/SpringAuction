package com.lead.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BadStateException extends RuntimeException {
    public BadStateException(String message) {
        super(message);
    }
}
