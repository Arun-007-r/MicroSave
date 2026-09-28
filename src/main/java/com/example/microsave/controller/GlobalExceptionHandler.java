package com.example.microsave.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRuntimeException(
            RuntimeException exception) {

        return Map.of(
                "error", exception.getMessage()
        );
    }
    @ExceptionHandler(
        org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException exception) {

        return Map.of(
                "error",
                exception.getBindingResult()
                        .getAllErrors()
                        .get(0)
                        .getDefaultMessage()
        );
    }
}