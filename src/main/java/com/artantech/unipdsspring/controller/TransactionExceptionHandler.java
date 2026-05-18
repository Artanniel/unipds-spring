package com.artantech.unipdsspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.artantech.unipdsspring.events.ErrorDTO;
import com.artantech.unipdsspring.events.InvalidAccountException;
import com.artantech.unipdsspring.events.InvalidBalanceAccountException;
import com.artantech.unipdsspring.events.InvalidTransferException;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<ErrorDTO> handleInvalidAccountException(InvalidAccountException e) {
        return ResponseEntity.status(404).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(InvalidBalanceAccountException.class)
    public ResponseEntity<ErrorDTO> handleInvalidBalanceAccountException(InvalidBalanceAccountException e) {
        return ResponseEntity.status(400).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(InvalidTransferException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTransferException(Exception e) {
        return ResponseEntity.status(500).body(new ErrorDTO(e.getMessage()));
    }
}
