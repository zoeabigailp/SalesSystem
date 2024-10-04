package com.salessystem.SalesSystem.exceptions;

public class IdIsExistsException extends RuntimeException {
    public IdIsExistsException(String message) {
        super(message);
    }

    public IdIsExistsException() {
    }
}
