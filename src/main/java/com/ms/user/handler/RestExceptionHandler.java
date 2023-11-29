package com.ms.user.handler;

import com.ms.user.exceptions.ConflictException;
import com.ms.user.exceptions.ConflictExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handleConflictException(ConflictException conflict){
        return new ResponseEntity<>(
                ConflictExceptionDetails.builder()
                        .title("Conflict error")
                        .status(HttpStatus.CONFLICT.value())
                        .details(conflict.getMessage())
                        .developerMessage(conflict.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.CONFLICT);
    }
}
