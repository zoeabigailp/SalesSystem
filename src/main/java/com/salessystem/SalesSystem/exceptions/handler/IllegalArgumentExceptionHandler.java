package com.salessystem.SalesSystem.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> IllegalArgumentExceptionHandler(IllegalArgumentException e){
        Map<String, String> messenges = new HashMap<>();
        messenges.put("Messenge", e.getLocalizedMessage());
        return messenges;
    }
}
