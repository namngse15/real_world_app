package com.example.real_world_app.model.arrticle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTOFilter {
    private String tag;
    private String author;
    private String favorited;
    private int limit;
    private int offset;  
}
