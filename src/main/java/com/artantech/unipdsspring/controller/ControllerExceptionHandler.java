package com.artantech.unipdsspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.artantech.unipdsspring.events.ErrorDTO;
import com.artantech.unipdsspring.events.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(exception = RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntime(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(e.getMessage()));
    }

}
