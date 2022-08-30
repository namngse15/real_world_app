package com.example.real_world_app.service;

import java.util.Map;

import com.example.real_world_app.exception.custom.NotFoundCustomException;
import com.example.real_world_app.model.profile.dto.ProfileDtoRes;

public interface ProfileService {

    Map<String, ProfileDtoRes> getProfile(String username) throws NotFoundCustomException ;

    Map<String, ProfileDtoRes> following(String username) throws NotFoundCustomException ;

    Map<String, ProfileDtoRes> unfollowing(String username) throws NotFoundCustomException ;
    
}
