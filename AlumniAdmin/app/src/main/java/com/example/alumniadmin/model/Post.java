package com.example.alumniadmin.model;

import java.util.HashMap;
import java.util.Map;

public class Post {

    private String id;
    private String title;
    private String caption;
    private String contents;
    private String imageUrl;
    private String userId;
    private User user;

    public Post() { }

    public Post(String id, String title, String caption, String imageUrl, String userId) {
        this.id = id;
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    //@Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", title);
        result.put("caption", caption);
        result.put("contents", contents);
        result.put("imageUrl", imageUrl);
        result.put("userId", userId);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
