package com.example.real_world_app.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.real_world_app.exception.custom.BadRequestCustomException;
import com.example.real_world_app.exception.custom.InternalServerCustomeException;
import com.example.real_world_app.model.user.dto.UserDtoReqAuthen;
import com.example.real_world_app.model.user.dto.UserDtoReqRegist;
import com.example.real_world_app.model.user.dto.UserDtoRes;
import com.example.real_world_app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    
    private final UserService userService;

    @PostMapping("/users/login")
    public Map<String, UserDtoRes> login(@RequestBody Map<String, UserDtoReqAuthen> req) throws BadRequestCustomException  {
        return userService.authenticate(req);
    }

    @PostMapping("/users")
    public Map<String, UserDtoRes> registration(@RequestBody Map<String, UserDtoReqRegist> req) throws InternalServerCustomeException {
        return userService.register(req);
    }
    
}
