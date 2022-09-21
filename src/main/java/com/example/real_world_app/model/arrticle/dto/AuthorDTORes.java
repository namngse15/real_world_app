package com.example.real_world_app.model.arrticle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTORes {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
