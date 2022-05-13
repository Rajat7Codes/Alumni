package com.example.alumniadmin.fragement.users;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alumniadmin.model.Post;
import com.example.alumniadmin.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> allUsers;

     private DatabaseReference databaseReference;

    public UsersViewModel() {
        allUsers = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allUsers.setValue( new ArrayList<>());

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    allUsers.getValue().add( user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public MutableLiveData<ArrayList<User>> getUsers() {
        return allUsers;
    }
}