package com.example.real_world_app.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoReqUpdate {
    
    private String email;
    private String bio;
    private String image;
}
