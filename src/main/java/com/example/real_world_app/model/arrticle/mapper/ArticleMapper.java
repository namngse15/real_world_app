package com.example.real_world_app.model.arrticle.mapper;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.real_world_app.entity.Article;
import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.arrticle.dto.ArticleDTOCreate;
import com.example.real_world_app.model.arrticle.dto.ArticleDTORes;
import com.example.real_world_app.model.arrticle.dto.AuthorDTORes;
import com.example.real_world_app.utils.SlugUtil;

@Component
public class ArticleMapper {
    public Article to(ArticleDTOCreate articleDTOCreate) { 

        Article article = new Article();
        BeanUtils.copyProperties(articleDTOCreate, article);
        article.setSlug(SlugUtil.getSlug(articleDTOCreate.getTitle()));
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        return article;
    }

    public ArticleDTORes to(Article article, boolean favorited, int favoritesCount 
    ,boolean isFollowing) {
        ArticleDTORes articleDTORes = new ArticleDTORes();
        BeanUtils.copyProperties(article, articleDTORes);
        articleDTORes.setFavorited(favorited);
        articleDTORes.setFavoritesCount(favoritesCount);
        articleDTORes.setAuthor(to(article.getAuthor(), isFollowing));
        return articleDTORes;
    }

    private AuthorDTORes to(User author, boolean isFollowing) {
        AuthorDTORes authorDTORes = new AuthorDTORes();
        BeanUtils.copyProperties(author, authorDTORes);
        authorDTORes.setFollowing(isFollowing);
        return authorDTORes;
    }
}
