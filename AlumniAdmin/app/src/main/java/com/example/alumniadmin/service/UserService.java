package com.example.alumniadmin.service;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.alumniadmin.model.Admin;
import com.example.alumniadmin.model.User;
import com.example.alumniadmin.util.CallBack;

public interface UserService {
    public void saveUser(CallBack<Boolean> finishedCallBack, User user);
    public void getSurvey(CallBack<Boolean> finishedCallBack, String bio, Uri imageURI);

    void getSurvey(CallBack<Boolean> finishedCallBack, String bio);

    public void getUserById(@NonNull CallBack<User> finishedCallback, String id);

    void getAdminByIdAndPassword(@NonNull CallBack<Admin> callback, String mail, String password);

    public void getCurrentUser(@NonNull CallBack<User> finishedCallback);
}
