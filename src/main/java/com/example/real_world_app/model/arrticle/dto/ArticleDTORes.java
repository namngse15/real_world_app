package com.example.real_world_app.model.arrticle.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTORes {
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private Date createdAt;
    private Date updatedAt;
    private boolean favorited;
    private Integer favoritesCount;
    private AuthorDTORes author;
}
