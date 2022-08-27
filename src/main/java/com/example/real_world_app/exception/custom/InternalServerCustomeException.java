package com.example.real_world_app.exception.custom;

import com.example.real_world_app.model.error.CustomError;

public class InternalServerCustomeException extends BaseCustomException {

    public InternalServerCustomeException(CustomError customError) {
        super(customError);
    }
    
}
