package com.example.alumniadmin.model;

import java.util.HashMap;

public class Admin {

    private String id;
    private String name;
    private String email;
    private String password;
    private String pic;
    private HashMap<String, Post> posts;

    public Admin() { }

    public Admin(String name, String email, String phone) {
        this.name = name;
        this.email = email;
    }

    public Admin(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public HashMap<String, Post> getPosts() {
        return posts;
    }

    public void setPosts(HashMap<String, Post> posts) {
        this.posts = posts;
    }
}
