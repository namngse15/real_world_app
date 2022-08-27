package com.example.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.real_world_app.entity.User;
import com.example.real_world_app.exception.custom.BadRequestCustomException;
import com.example.real_world_app.exception.custom.InternalServerCustomeException;
import com.example.real_world_app.model.error.CustomError;
import com.example.real_world_app.model.user.dto.UserDtoReqAuthen;
import com.example.real_world_app.model.user.dto.UserDtoReqRegist;
import com.example.real_world_app.model.user.dto.UserDtoRes;
import com.example.real_world_app.model.user.mapper.UserMapper;
import com.example.real_world_app.repository.UserRepository;
import com.example.real_world_app.service.UserService;
import com.example.real_world_app.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, UserDtoRes> authenticate(Map<String, UserDtoReqAuthen> req) throws BadRequestCustomException {
        UserDtoReqAuthen userDtoReqAuthen = req.get("user");
        Optional<User> userOptional = userRepository.findByEmail(userDtoReqAuthen.getEmail());
        if(!isAuthenticated(userDtoReqAuthen, userOptional)) {
            throw new BadRequestCustomException(CustomError.builder()
            .code("400")
            .message("Username and Password are incorrect")
            .build());
        }
        return userDtoRes(userOptional.get());
    }

    private boolean isAuthenticated(UserDtoReqAuthen userDtoReqAuthen, Optional<User> userOptional) {
        boolean isAuthen = false;
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            // check if user
            if(passwordEncoder.matches(userDtoReqAuthen.getPassword(), user.getPassword())) {
                isAuthen = true;
            }
        }
        return isAuthen;
    }

    @Override
    public Map<String, UserDtoRes> register(Map<String, UserDtoReqRegist> req) throws InternalServerCustomeException {
        UserDtoReqRegist userDtoReqRegist = req.get("user");
        Optional<User> userOptional = userRepository.findByEmail(userDtoReqRegist.getEmail());
        if(userOptional.isPresent()) {
            throw new InternalServerCustomeException(CustomError.builder()
            .code("500")
            .message("Email already exists")
            .build());
        }
        User user = userMapper.to(userDtoReqRegist);
        //Encrypt the password
        user.setPassword(passwordEncoder.encode(userDtoReqRegist.getPassword()));
        user = userRepository.save(user);
        return userDtoRes(user);
    }

    private Map<String, UserDtoRes> userDtoRes(User user) {
        Map<String,UserDtoRes> res = new HashMap<>();
        UserDtoRes userDtoRes = userMapper.to(user); 
        userDtoRes.setToken(jwtTokenUtil.generateToken(user, (long) (24 * 60 * 60)));// 1 day
        res.put("user", userDtoRes);
        return res;
    }
    
}
