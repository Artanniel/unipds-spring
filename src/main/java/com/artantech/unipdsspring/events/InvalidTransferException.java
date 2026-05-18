package com.artantech.unipdsspring.events;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String msg) {
        super(msg);
    }
}
