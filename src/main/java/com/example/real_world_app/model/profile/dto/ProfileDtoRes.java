package com.example.real_world_app.model.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDtoRes {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
