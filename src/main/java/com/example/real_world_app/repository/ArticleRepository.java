package com.example.real_world_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.real_world_app.entity.Article;

public interface ArticleRepository extends JpaRepository<Article,Integer>
{

    Article findBySlug(String slug);
    
}
