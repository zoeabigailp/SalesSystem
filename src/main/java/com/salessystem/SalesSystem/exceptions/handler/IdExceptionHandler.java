package com.salessystem.SalesSystem.exceptions.handler;

import com.salessystem.SalesSystem.exceptions.IdIsExistsException;
import com.salessystem.SalesSystem.exceptions.IdNoExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class IdExceptionHandler {

    @ExceptionHandler(IdIsExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,String> CategoryNoExistsExceptionHandler(IdIsExistsException e){
        Map<String, String> messenges = new HashMap<>();
        messenges.put("Messenge", e.getLocalizedMessage());
        return messenges;
    }

    @ExceptionHandler(IdNoExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> IdNoExistsExceptionHandler(IdNoExistsException e){
        Map<String, String> messenges = new HashMap<>();
        messenges.put("Messenge", e.getLocalizedMessage());
        return messenges;
    }
}
