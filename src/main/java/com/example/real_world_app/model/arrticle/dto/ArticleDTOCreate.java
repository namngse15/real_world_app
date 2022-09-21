package com.example.real_world_app.model.arrticle.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTOCreate {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
}
