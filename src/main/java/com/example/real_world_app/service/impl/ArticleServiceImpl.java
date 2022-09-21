package com.example.real_world_app.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.real_world_app.entity.Article;
import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.arrticle.dto.ArticleDTOCreate;
import com.example.real_world_app.model.arrticle.dto.ArticleDTORes;
import com.example.real_world_app.model.arrticle.mapper.ArticleMapper;
import com.example.real_world_app.repository.ArticleRepository;
import com.example.real_world_app.service.ArticleService;
import com.example.real_world_app.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ProfileService profileService;
    private final ArticleMapper articleMapper;

    @Override
    public Map<String, ArticleDTORes> createArticles(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        ArticleDTOCreate articleDTOCreate = articleDTOCreateMap.get("article");
        Article article = articleMapper.to(articleDTOCreate);
        User cUser = profileService.getUserLoggedIn();
        article.setAuthor(cUser);
        article = articleRepository.save(article);
        Map<String,ArticleDTORes> wrapper= new HashMap<>();
        ArticleDTORes articleDTORes = articleMapper.to(article, false, 0, false);
        wrapper.put("article",articleDTORes);
        return wrapper;
    }

    @Override
    public Map<String, ArticleDTORes> getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug);
        Map<String,ArticleDTORes> wrapper= new HashMap<>();

        User userLoggedIn = profileService.getUserLoggedIn();
        User author = article.getAuthor();
        Set<User> followers = author.getFollowers();
        boolean isFollowing = false;
        for (User user : followers) {
            if (user.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        ArticleDTORes articleDTORes = articleMapper.to(article, false, 0, isFollowing);
        wrapper.put("article",articleDTORes);
        return wrapper;
    }
    
}
