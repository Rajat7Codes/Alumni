package com.example.collegealumni.fragment.college;

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
import com.example.collegealumni.adapter.CollegeAdapter;
import com.example.collegealumni.adapter.FeedAdapter;

import java.util.ArrayList;

public class CollegeFragment extends Fragment {

    private CollegeViewModel collegeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        collegeViewModel = new ViewModelProvider( this).get( CollegeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_college, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.collegeRecyclerView);
        recyclerView.setAdapter( new FeedAdapter( new ArrayList<>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        collegeViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList posts) {
                recyclerView.setAdapter( new CollegeAdapter( posts));
            }
        });

        return root;
    }
}