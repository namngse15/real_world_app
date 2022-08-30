package com.example.real_world_app.service;

import java.util.Map;

import com.example.real_world_app.exception.custom.BadRequestCustomException;
import com.example.real_world_app.exception.custom.InternalServerCustomException;
import com.example.real_world_app.exception.custom.NotFoundCustomException;
import com.example.real_world_app.model.user.dto.UserDtoReqAuthen;
import com.example.real_world_app.model.user.dto.UserDtoReqRegist;
import com.example.real_world_app.model.user.dto.UserDtoReqUpdate;
import com.example.real_world_app.model.user.dto.UserDtoRes;

public interface UserService {

    Map<String, UserDtoRes> authenticate(Map<String, UserDtoReqAuthen> req) throws BadRequestCustomException;

    Map<String, UserDtoRes> register(Map<String, UserDtoReqRegist> req) throws InternalServerCustomException;

    Map<String, UserDtoRes> getCurrentUser() throws NotFoundCustomException;

    Map<String, UserDtoRes> updateCurrentUser(Map<String, UserDtoReqUpdate> req) throws NotFoundCustomException;
    
}
