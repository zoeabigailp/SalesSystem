package com.salessystem.SalesSystem.Validation;

import org.springframework.stereotype.Component;

@Component
public class NormalizeValid {

    //Normalize string
    public String NormalizeCad(String text){
        text = text.trim();
        text = text.toLowerCase();
        return text;
    }
}
