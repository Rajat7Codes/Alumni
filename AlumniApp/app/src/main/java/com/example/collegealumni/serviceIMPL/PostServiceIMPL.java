package com.example.collegealumni.serviceIMPL;

import androidx.annotation.NonNull;

import com.example.collegealumni.model.Post;
import com.example.collegealumni.service.PostService;
import com.example.collegealumni.util.CallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PostServiceIMPL implements PostService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public PostServiceIMPL() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getPostById(@NonNull CallBack<Post> finishedCallback, String id) {

    }

    @Override
    public void savePost(@NonNull CallBack<Boolean> finishedCallback, Post post) {
        databaseReference.child("posts").child( post.getId()).setValue( post)
                .addOnCompleteListener( task ->
                        finishedCallback.callback( task.isSuccessful()))
                .addOnFailureListener( e ->
                        finishedCallback.callback( false));
    }

    @Override
    public void deletePost(@NonNull CallBack<Boolean> finishedCallback, Post post) {
        databaseReference.child( "posts").child( post.getId())
                .removeValue()
                .addOnCompleteListener(
                        task -> databaseReference.child( "users").child( post.getUserId())
                        .child( "posts").child( post.getId())
                        .removeValue()
                                .addOnCompleteListener( task1 -> finishedCallback.callback( task1.isSuccessful()) ));
    }

    @Override
    public void updatePost(@NonNull CallBack<Boolean> finishedCallback, Post post) {
        String key = post.getId();
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/users/" + firebaseAuth.getUid() + "/posts/" + key, postValues);

        databaseReference
                .updateChildren( childUpdates)
                .addOnCompleteListener( task ->  finishedCallback.callback( task.isSuccessful()));
    }
}
