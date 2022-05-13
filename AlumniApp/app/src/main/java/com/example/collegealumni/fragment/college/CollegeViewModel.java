package com.example.collegealumni.fragment.college;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collegealumni.model.Post;
import com.example.collegealumni.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CollegeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> allPosts;

    private DatabaseReference databaseReference;

    public CollegeViewModel() {
        allPosts = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("admins").child("posts");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allPosts.setValue( new ArrayList<>());

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    allPosts.getValue().add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public MutableLiveData<ArrayList<Post>> getPosts() {
        return allPosts;
    }
}