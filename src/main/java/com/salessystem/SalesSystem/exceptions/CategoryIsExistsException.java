package com.salessystem.SalesSystem.exceptions;

public class CategoryIsExistsException extends RuntimeException{
    public CategoryIsExistsException(String message) {
        super(message);
    }

    public CategoryIsExistsException() {
    }
}
