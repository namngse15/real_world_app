package com.example.real_world_app.service;

import java.util.Map;

import com.example.real_world_app.model.arrticle.dto.ArticleDTOCreate;
import com.example.real_world_app.model.arrticle.dto.ArticleDTORes;

public interface ArticleService {

    Map<String, ArticleDTORes> createArticles(Map<String, ArticleDTOCreate> articleDTOCreate);

    Map<String, ArticleDTORes> getArticleBySlug(String slug);
    
}
