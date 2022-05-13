package com.example.collegealumni.service;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.collegealumni.model.User;
import com.example.collegealumni.util.CallBack;

public interface UserService {
    public void saveUser(CallBack<Boolean> finishedCallBack, User user);
    public void getSurvey(CallBack<Boolean> finishedCallBack, String bio, Uri imageURI);

    void getSurvey(CallBack<Boolean> finishedCallBack, String bio);

    public void getUserById(@NonNull CallBack<User> finishedCallback, String id);
    public void getCurrentUser(@NonNull CallBack<User> finishedCallback);
}
