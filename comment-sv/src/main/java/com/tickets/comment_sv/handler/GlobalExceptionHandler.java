package com.tickets.comment_sv.handler;

import com.tickets.comment_sv.dto.ApiError;
import com.tickets.comment_sv.exception.TicketException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ApiError> handlerEntityNotFoundException(EntityNotFoundException e){
        log.error(String.valueOf(e));

        ApiError apiError = new ApiError("Entity not found", e.getMessage(), BAD_REQUEST.value());

        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(TicketException.class)
    private ResponseEntity<ApiError> handlerCriminalCrossException(TicketException e){
        log.error(String.valueOf(e));

        ApiError apiError = new ApiError(e.getCode(), e.getMessage(), BAD_REQUEST.value());

        return ResponseEntity.status(apiError.status()).body(apiError);
    }
}
