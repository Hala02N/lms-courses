package com.example.lmscourses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseCreationException extends RuntimeException{
    public CourseCreationException(String message){
        super(message);
    }
}
