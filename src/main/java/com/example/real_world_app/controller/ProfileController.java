package com.example.real_world_app.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.real_world_app.exception.custom.NotFoundCustomException;
import com.example.real_world_app.model.profile.dto.ProfileDtoRes;
import com.example.real_world_app.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles/{username}")
public class ProfileController {
    
    private final ProfileService profileService;

    @GetMapping("")
    public Map<String, ProfileDtoRes> getProfile(@PathVariable String username) throws NotFoundCustomException {
        return profileService.getProfile(username);
    }

    @PostMapping("/follow")
    public Map<String, ProfileDtoRes> following(@PathVariable String username) throws NotFoundCustomException {
        return profileService.following(username);
    }
    
    @DeleteMapping("/unfollow")
    public Map<String, ProfileDtoRes> unfollowing(@PathVariable String username) throws NotFoundCustomException {
        return profileService.unfollowing(username);
    }
    
}
