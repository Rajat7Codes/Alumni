package com.example.collegealumni.fragment.search;

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
import java.util.Locale;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> allPosts;

    private DatabaseReference databaseReference;

    public SearchViewModel() {
        allPosts = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Post>> getPosts( String keyword) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allPosts.setValue( new ArrayList<>());

                for ( DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue( User.class);

                    for( DataSnapshot tmpSnapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot postSnapshot : tmpSnapshot.getChildren()) {
                            if (postSnapshot.hasChildren()) {
                                Post post = postSnapshot.getValue(Post.class);
                                if( post.getTitle().toLowerCase().startsWith( keyword.trim().toLowerCase())) {
                                    post.setUser(user);
                                    allPosts.getValue().add( post);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        return allPosts;
    }
}