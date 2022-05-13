package com.example.collegealumni.fragment.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.R;
import com.example.collegealumni.adapter.FeedAdapter;
import com.example.collegealumni.model.Post;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private FeedViewModel feedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        feedViewModel = new ViewModelProvider( this).get( FeedViewModel.class);

        View root = inflater.inflate(R.layout.fragment_feed, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.feedRecyclerView);
        recyclerView.setAdapter( new FeedAdapter( new ArrayList<>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        feedViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList posts) {
                recyclerView.setAdapter( new FeedAdapter( posts));
            }
        });

        return root;
    }
}