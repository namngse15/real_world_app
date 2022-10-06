package com.example.real_world_app.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.real_world_app.model.arrticle.dto.ArticleDTOCreate;
import com.example.real_world_app.model.arrticle.dto.ArticleDTOFilter;
import com.example.real_world_app.model.arrticle.dto.ArticleDTORes;
import com.example.real_world_app.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    
    private final ArticleService articleService;

    @PostMapping("")
    public Map<String, ArticleDTORes> createArticle(
        @RequestBody Map<String, ArticleDTOCreate> articleDTOCreate) {
        return articleService.createArticles(articleDTOCreate);
    }

    @GetMapping("/{slug}")
    public Map<String, ArticleDTORes> getArticleBySlug(@PathVariable String slug) {
        return articleService.getArticleBySlug(slug);
    }

    @GetMapping()
    public Map<String, Object> getListArticles(@RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset) {

        ArticleDTOFilter filter = ArticleDTOFilter.builder().tag(tag).author(author).favorited(favorited).limit(limit)
                .offset(offset).build();
        return articleService.getListArticles(filter);
    }
}
