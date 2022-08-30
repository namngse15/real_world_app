package com.example.real_world_app.exception.custom;

import com.example.real_world_app.model.error.CustomError;

public class NotFoundCustomException extends BaseCustomException {

    public NotFoundCustomException(CustomError customError) {
        super(customError);
    }

    
}
