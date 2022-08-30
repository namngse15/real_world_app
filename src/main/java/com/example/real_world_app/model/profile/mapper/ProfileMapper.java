package com.example.real_world_app.model.profile.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.profile.dto.ProfileDtoRes;

@Component
public class ProfileMapper {
    public ProfileDtoRes to(User user) {
        ProfileDtoRes profileDtoRes = new ProfileDtoRes();
        BeanUtils.copyProperties(user, profileDtoRes);
        return profileDtoRes;
    }
}
