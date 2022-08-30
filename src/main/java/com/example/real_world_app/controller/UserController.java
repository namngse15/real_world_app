package com.example.real_world_app.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.real_world_app.exception.custom.BadRequestCustomException;
import com.example.real_world_app.exception.custom.InternalServerCustomException;
import com.example.real_world_app.exception.custom.NotFoundCustomException;
import com.example.real_world_app.model.user.dto.UserDtoReqAuthen;
import com.example.real_world_app.model.user.dto.UserDtoReqRegist;
import com.example.real_world_app.model.user.dto.UserDtoReqUpdate;
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
    public Map<String, UserDtoRes> registration(@RequestBody Map<String, UserDtoReqRegist> req) throws InternalServerCustomException {
        return userService.register(req);
    }

    @GetMapping("/user")
    public Map<String, UserDtoRes> getCurrentUser() throws NotFoundCustomException {
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public Map<String, UserDtoRes> updateCurrentUser(@RequestBody Map<String, UserDtoReqUpdate> req) throws NotFoundCustomException  {
        return userService.updateCurrentUser(req);
    }
    
}
