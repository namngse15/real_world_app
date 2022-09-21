package com.example.real_world_app.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Entity
@Table(name="article_tbl")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private String tagList;
    private Date createdAt;
    private Date updatedAt;
    private boolean favorited;
    private Integer favoritesCount;

    public List<String> getTagList() {
        return Arrays.asList(this.tagList.split(";"));
    }

    public void setTagList(List<String> tagList) {
        StringBuilder sb = new StringBuilder();
        for(String tag : tagList) {
            sb.append(tag).append(";");
        }
        this.tagList = sb.substring(0,sb.length() - 1).toString();
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
