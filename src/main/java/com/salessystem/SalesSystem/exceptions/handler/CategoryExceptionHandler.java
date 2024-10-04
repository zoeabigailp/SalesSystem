package com.salessystem.SalesSystem.exceptions.handler;

import com.salessystem.SalesSystem.exceptions.CategoryIsExistsException;
import com.salessystem.SalesSystem.exceptions.CategoryNoExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryIsExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> CategoryExceptionHandler(CategoryIsExistsException e){
        Map<String, String> messenges = new HashMap<>();
        messenges.put("Messenge", e.getLocalizedMessage());
        return messenges;
    }

    @ExceptionHandler(CategoryNoExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,String> CategoryNoExistsExceptionHandler(CategoryNoExistsException e){
        Map<String, String> messenges = new HashMap<>();
        messenges.put("Messenge", e.getLocalizedMessage());
        return messenges;
    }
}
