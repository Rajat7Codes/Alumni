package com.example.alumniadmin.fragement.myposts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumniadmin.AddPostActivity;
import com.example.alumniadmin.MainActivity;
import com.example.alumniadmin.R;
import com.example.alumniadmin.adapter.MyPostsAdapter;

import java.util.ArrayList;

public class MyPostsFragment extends Fragment {

    private MyPostsViewModel myPostsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myPostsViewModel = new ViewModelProvider( this).get( MyPostsViewModel.class);

        View root = inflater.inflate( R.layout.fragment_my_posts, container, false);

        Button addPostBtn = root.findViewById( R.id.addPostBtn);

        final RecyclerView recyclerView = root.findViewById(R.id.myPostRecyclerView);
        recyclerView.setAdapter( new MyPostsAdapter( new ArrayList<>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getContext(), AddPostActivity.class);
                startActivity( intent);
            }
        });

        myPostsViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList posts) {
                recyclerView.setAdapter( new MyPostsAdapter( posts));
            }
        });

        return root;
    }
}