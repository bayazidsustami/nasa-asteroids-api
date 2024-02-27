package com.example.asteroids.nasa.controller;

import com.example.asteroids.nasa.models.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> apiException(
            ResponseStatusException exception
    ){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ErrorResponse.builder()
                        .message(exception.getReason())
                        .build()
                );
    }

}
