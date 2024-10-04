package com.salessystem.SalesSystem.exceptions;

public class IdNoExistsException extends RuntimeException {
    public IdNoExistsException(String message) {
        super(message);
    }

  public IdNoExistsException() {
  }
}
