package com.example.real_world_app.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRes {

    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

}
