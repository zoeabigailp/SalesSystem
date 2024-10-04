package com.salessystem.SalesSystem.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.SizeLimitExceededException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,String> GlobalExceptionHandler(MethodArgumentNotValidException e){
        Map<String, String> messenges = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    messenges.put("Messenge", error.getDefaultMessage());
                }
        );
        return messenges;
    }
}
