package com.example.collegealumni;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.adapter.MyPostsAdapter;
import com.example.collegealumni.model.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPostActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private ImageView postImage, userImage;
    private TextView postTitle, postCaption, userNameView;
    private String postId, userName, userPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        postImage = findViewById(R.id.postImage);
        userImage = findViewById(R.id.profileImageView);
        postTitle = findViewById(R.id.postTitle);
        postCaption = findViewById(R.id.postCaption);
        userNameView = findViewById(R.id.userNameView);

        postId = getIntent().getStringExtra( "postId");
        userName = getIntent().getStringExtra( "userName");
        userPic = getIntent().getStringExtra( "userPic");

        if( userName != null && userPic != null) {
            databaseReference.child("posts").child(postId).get()
                    .addOnCompleteListener(task -> {
                        Post post = task.getResult().getValue(Post.class);

                        postTitle.setText(post.getTitle());
                        postCaption.setText(post.getCaption());

                        Picasso.get()
                                .load(post.getImageUrl())
                                .into(postImage);

                        userNameView.setText(userName);
                        Picasso.get()
                                .load(userPic)
                                .into(userImage);

                    });
        } else {
            databaseReference.child("admins").child("posts").child(postId).get()
                    .addOnCompleteListener(task -> {
                        Post post = task.getResult().getValue(Post.class);

                        postTitle.setText(post.getTitle());
                        postCaption.setText(post.getCaption());

                        Picasso.get()
                                .load(post.getImageUrl())
                                .into(postImage);
                        userNameView.setText("Admin");
                        Picasso.get()
                                .load(R.drawable.app_logo)
                                .into(userImage);
                    });
        }
    }
}