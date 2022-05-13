package com.example.alumniadmin.fragement.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alumniadmin.model.User;
import com.example.alumniadmin.service.UserService;
import com.example.alumniadmin.serviceIMPL.UserServiceIMPL;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;

    // private UserService userService = new UserServiceIMPL();

    public ProfileViewModel() {
        user = new MutableLiveData<>();

        // userService.getCurrentUser(userNew -> user.setValue( userNew));
    }

    public MutableLiveData<User> getUser() {
        return user;
    }
}