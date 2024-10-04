package com.salessystem.SalesSystem.exceptions;

public class CategoryNoExistsException extends RuntimeException {
    public CategoryNoExistsException(String message) {
        super(message);
    }

  public CategoryNoExistsException() {
  }
}
