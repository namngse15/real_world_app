package com.example.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.exception.custom.NotFoundCustomException;
import com.example.real_world_app.model.error.CustomError;
import com.example.real_world_app.model.profile.dto.ProfileDtoRes;
import com.example.real_world_app.model.profile.mapper.ProfileMapper;
import com.example.real_world_app.repository.ProfileRepository;
import com.example.real_world_app.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Map<String, ProfileDtoRes> getProfile(String username) throws NotFoundCustomException {
        User userLoggedIn = getUserLoggedIn();
        Optional<User> profileOptional = profileRepository.findByUsername(username);
        if (profileOptional.isEmpty()) {
            throw new NotFoundCustomException(CustomError.builder()
                    .code("404")
                    .message("User not found.")
                    .build());
        }
        Set<User> followers = profileOptional.get().getFollowers();
        boolean isFollowing = false;
        for (User user : followers) {
            if (user.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        return profileDtoRes(profileOptional.get(), isFollowing);
    }

    private Map<String, ProfileDtoRes> profileDtoRes(User user, boolean isFollowing) {
        Map<String, ProfileDtoRes> res = new HashMap<>();
        ProfileDtoRes profileDtoRes = profileMapper.to(user);
        profileDtoRes.setFollowing(isFollowing);
        res.put("user", profileDtoRes);
        return res;
    }

    @Override
    public User getUserLoggedIn() {
        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (prinicipal instanceof UserDetails) {
            String email = ((UserDetails) prinicipal).getUsername();
            return profileRepository.findByEmail(email).get();
        }
        return null;
    }

    @Override
    public Map<String, ProfileDtoRes> following(String username) throws NotFoundCustomException {
        User userLoggedIn = getUserLoggedIn();
        Optional<User> profileOptional = profileRepository.findByUsername(username);
        if (profileOptional.isEmpty()) {
            throw new NotFoundCustomException(CustomError.builder()
                    .code("404")
                    .message("User not found.")
                    .build());
        }
        User user = profileOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        if(!isFollowing) {
            isFollowing = true;
            user.getFollowers().add(userLoggedIn);
            user = profileRepository.save(user);
            isFollowing = true;
        }
        return profileDtoRes(profileOptional.get(), isFollowing);
    }

    @Override
    public Map<String, ProfileDtoRes> unfollowing(String username) throws NotFoundCustomException {
        User userLoggedIn = getUserLoggedIn();
        Optional<User> profileOptional = profileRepository.findByUsername(username);
        if (profileOptional.isEmpty()) {
            throw new NotFoundCustomException(CustomError.builder()
                    .code("404")
                    .message("User not found.")
                    .build());
        }
        User user = profileOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        if(isFollowing) {
            isFollowing = true;
            user.getFollowers().remove(userLoggedIn);
            user = profileRepository.save(user);
            isFollowing = false;
        }
        return profileDtoRes(profileOptional.get(), isFollowing);
    }
}
