package com.lead.service.auction.exception;

import com.lead.common.exception.GeneralExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuctionExceptionHandler extends GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleInvalidDateRangeException(InvalidDateRangeException e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
