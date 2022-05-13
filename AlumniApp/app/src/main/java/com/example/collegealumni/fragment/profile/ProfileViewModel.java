package com.example.collegealumni.fragment.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collegealumni.model.User;
import com.example.collegealumni.service.UserService;
import com.example.collegealumni.serviceIMPL.UserServiceIMPL;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;

    private UserService userService = new UserServiceIMPL();

    public ProfileViewModel() {
        user = new MutableLiveData<>();

        userService.getCurrentUser(userNew -> user.setValue( userNew));
    }

    public MutableLiveData<User> getUser() {
        return user;
    }
}