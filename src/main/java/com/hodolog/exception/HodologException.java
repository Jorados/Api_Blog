package com.hodolog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class HodologException extends RuntimeException{
    public HodologException(String message) {
        super(message);
    }

    public HodologException(String message, Throwable cause) {
        super(message, cause);
    }
    private final Map<String,String> validation = new HashMap<>();

    public void addValidation(String fieldName, String message){
        validation.put(fieldName,message);
    }
    public abstract int getStatusCode();
}
