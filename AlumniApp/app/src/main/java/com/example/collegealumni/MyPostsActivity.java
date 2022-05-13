package com.example.collegealumni;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.adapter.MyPostsAdapter;
import com.example.collegealumni.model.Post;

import java.util.ArrayList;

public class MyPostsActivity extends AppCompatActivity {

    MyPostViewModel myPostViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        myPostViewModel = new MyPostViewModel();

        final RecyclerView recyclerView = findViewById(R.id.myPostRecyclerView);
        recyclerView.setAdapter( new MyPostsAdapter( new ArrayList<Post>()));

        LinearLayoutManager llm = new LinearLayoutManager( getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        myPostViewModel.getPosts().observe( this, new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {
                recyclerView.setAdapter( new MyPostsAdapter( posts));
            }
        });
    }
}