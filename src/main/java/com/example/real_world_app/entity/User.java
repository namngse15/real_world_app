package com.example.real_world_app.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Data mặc định tạo ra equal, khi add vào set sẽ gây lỗi

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_tbl")
public class User {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private String email;
    private String password;
    @Column(unique=true)
    private String username;
    private String bio;
    private String image;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_follow", joinColumns = @JoinColumn(name = "follower_id")
    , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> followers;
    @ManyToMany(mappedBy = "followers")
    private Set<User> followings;
    @OneToMany(mappedBy = "author")
    private List<Article> articles;

}
