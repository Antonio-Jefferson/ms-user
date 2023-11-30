package com.ms.user.handler;

import com.ms.user.exceptions.ConflictException;
import com.ms.user.exceptions.ConflictExceptionDetails;
import com.ms.user.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad request Exception, Invalid Fields")
                        .status(HttpStatus.CONFLICT.value())
                        .details("Check the field(s) error")
                        .developerMessage( exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
