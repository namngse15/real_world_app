package com.example.real_world_app.model.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayload {
    private Integer uid;
    private String email;
}
