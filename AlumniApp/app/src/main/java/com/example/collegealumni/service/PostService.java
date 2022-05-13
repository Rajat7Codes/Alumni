package com.example.collegealumni.service;

import androidx.annotation.NonNull;

import com.example.collegealumni.model.Post;
import com.example.collegealumni.util.CallBack;

public interface PostService {
    public void getPostById(@NonNull CallBack<Post> finishedCallback, String id);
    public void savePost(@NonNull CallBack<Boolean> finishedCallback, Post post);
    public void deletePost(@NonNull CallBack<Boolean> finishedCallback, Post post);
    public void updatePost(@NonNull CallBack<Boolean> finishedCallback, Post post);
}
