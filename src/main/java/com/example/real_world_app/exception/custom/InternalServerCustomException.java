package com.example.real_world_app.exception.custom;

import com.example.real_world_app.model.error.CustomError;

public class InternalServerCustomException extends BaseCustomException {

    public InternalServerCustomException(CustomError customError) {
        super(customError);
    }
    
}
