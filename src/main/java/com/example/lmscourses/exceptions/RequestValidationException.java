package com.example.lmscourses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RequestValidationException extends RuntimeException{
    public RequestValidationException(String message){
        super(message);
    }
}
