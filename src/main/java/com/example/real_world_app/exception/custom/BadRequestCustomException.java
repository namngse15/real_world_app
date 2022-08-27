package com.example.real_world_app.exception.custom;

import com.example.real_world_app.model.error.CustomError;

public class BadRequestCustomException extends BaseCustomException {

    public BadRequestCustomException(CustomError customError) {
        super(customError);
    }
    
}
