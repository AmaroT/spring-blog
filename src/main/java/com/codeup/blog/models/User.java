package com.codeup.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Ad> ads;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;
    private String password;

    public User() {
    }

    public User(long id, String email, String username, List<Post> posts, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.posts = posts;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
    public List<Post> getPosts(){
       return posts;
    }
    public void setPosts(List<Post> posts){
        this.posts = posts;
    }

    public String getPassword(String password) { return password;}
    public void setPassword(String password) {this.password = password;}

}