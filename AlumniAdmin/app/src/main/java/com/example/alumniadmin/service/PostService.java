package com.example.alumniadmin.service;

import androidx.annotation.NonNull;

import com.example.alumniadmin.model.Post;
import com.example.alumniadmin.util.CallBack;

public interface PostService {
    public void getPostById(@NonNull CallBack<Post> finishedCallback, String id);
    public void savePost(@NonNull CallBack<Boolean> finishedCallback, Post post);
    public void deletePost(@NonNull CallBack<Boolean> finishedCallback, Post post);

    void deleteAdminPost(@NonNull CallBack<Boolean> finishedCallback, Post post);

    public void updatePost(@NonNull CallBack<Boolean> finishedCallback, Post post);

    void updateAdminPost(@NonNull CallBack<Boolean> finishedCallback, Post post);
}
