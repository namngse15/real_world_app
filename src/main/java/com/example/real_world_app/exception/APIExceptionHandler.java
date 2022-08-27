package com.example.real_world_app.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.real_world_app.exception.custom.BadRequestCustomException;
import com.example.real_world_app.exception.custom.InternalServerCustomeException;
import com.example.real_world_app.model.error.CustomError;

@RestControllerAdvice
public class APIExceptionHandler {
    
    @ExceptionHandler(BadRequestCustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,CustomError> badRequestException(BadRequestCustomException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(InternalServerCustomeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,CustomError> internalServerException(InternalServerCustomeException ex) {
        return ex.getErrors();
    }

}
