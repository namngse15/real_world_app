package com.example.real_world_app.repository.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.real_world_app.entity.Article;
import com.example.real_world_app.model.arrticle.dto.ArticleDTOFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
	private final EntityManager entityManager;

	public Map<String, Object> findAll(ArticleDTOFilter filter) {
		StringBuilder query = new StringBuilder(
                "select a from Article a left join a.author au left join a.userFavorited ufa where 1=1");
        Map<String, Object> params = new HashMap<>();
        if (filter.getTag() != null) {
            query.append(" and a.tagList like :tag");
            params.put("tag", "%" + filter.getTag() + "%");
        }
        if (filter.getAuthor() != null) {
            query.append(" and au.username = :author");
            params.put("author", filter.getAuthor());
        }
        if (filter.getFavorited() != null) {
            query.append(" and ufa.username = :favorited");
            params.put("favorited", filter.getFavorited());
        }

        TypedQuery<Article> tQuery = entityManager.createQuery(query.toString(), Article.class);
        Query countQuery = entityManager.createQuery(query.toString().replace("select a", "select count(a.id)"));

        System.out.println("query: " + query);
        params.forEach((k, v) -> {
            tQuery.setParameter(k, v);
            countQuery.setParameter(k, v);
        });

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());
        long totalArticle = (long) countQuery.getSingleResult();
        List<Article> listArticles = tQuery.getResultList();

        Map<String, Object> results = new HashMap<>();
        results.put("listArticles", listArticles);
        results.put("totalArticle", totalArticle);
        return results;
	}

}
