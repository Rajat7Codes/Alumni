package com.example.collegealumni;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collegealumni.model.Post;
import com.example.collegealumni.model.User;
import com.example.collegealumni.service.UserService;
import com.example.collegealumni.serviceIMPL.UserServiceIMPL;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MyPostViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> allPosts;
    private UserService userService = new UserServiceIMPL();

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public MyPostViewModel() {
        allPosts = new MutableLiveData<>();

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.child( firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Post> posts = new ArrayList<>();
                User user = dataSnapshot.getValue( User.class);

                if(  user.getPosts() != null) {
                    for( Map.Entry<String, Post> entry : user.getPosts().entrySet()) {
                        posts.add( entry.getValue());
                    }
                }

                allPosts.setValue( posts);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public MutableLiveData<ArrayList<Post>> getPosts() {
        return allPosts;
    }
}