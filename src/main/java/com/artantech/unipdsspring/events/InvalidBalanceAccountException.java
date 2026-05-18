package com.artantech.unipdsspring.events;

public class InvalidBalanceAccountException extends RuntimeException {
    public InvalidBalanceAccountException(String msg) {
        super(msg);
    }
}
