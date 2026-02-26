// card/impl/handler/CardExceptionHandler.java
package com.bank.management.card.api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.bank.management.card.exception.NotFoundCardException;
import com.bank.manager.card.api.model.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class CardExceptionHandler {

    @ExceptionHandler(NotFoundCardException.class)
    public ResponseEntity<ErrorResponse> handleCardNotFound(
            NotFoundCardException ex, WebRequest request
    ) {
        log.error("Card not found: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse();
        error.setCode("CARD_NOT_FOUND");
        error.setMessage(ex.getMessage());
        error.setTimestamp(ZonedDateTime.now().toOffsetDateTime());
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TransferFailedException.class)
    public ResponseEntity<ErrorResponse> handleTransferFailed(
            TransferFailedException ex, WebRequest request
    ) {
        log.error("Transfer failed: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse();
        error.setCode("TRANSFER_FAILED");
        error.setMessage(ex.getMessage());
        error.setTimestamp(ZonedDateTime.now().toOffsetDateTime());
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request
    ) {
        log.error("Invalid argument: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse();
        error.setCode("INVALID_REQUEST");
        error.setMessage(ex.getMessage());
        error.setTimestamp(ZonedDateTime.now().toOffsetDateTime());
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(
            IllegalStateException ex, WebRequest request
    ) {
        log.error("Invalid state: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse();
        error.setCode("INVALID_STATE");
        error.setMessage(ex.getMessage());
        error.setTimestamp(ZonedDateTime.now().toOffsetDateTime());
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}