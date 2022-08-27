package com.example.real_world_app.model.user.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.user.dto.UserDtoReqRegist;
import com.example.real_world_app.model.user.dto.UserDtoRes;

@Component
public class UserMapper {



    public UserDtoRes to(User user) {
        UserDtoRes userDtoRes = new UserDtoRes();
        BeanUtils.copyProperties(user, userDtoRes);
        return userDtoRes;
    }

    public User to(UserDtoReqRegist userDtoReqRegist) {
        User user = new User();
        BeanUtils.copyProperties(userDtoReqRegist, user);
        return user;
    }
}
