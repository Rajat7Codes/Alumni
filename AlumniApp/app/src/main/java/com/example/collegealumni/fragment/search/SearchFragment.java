package com.example.collegealumni.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.R;
import com.example.collegealumni.adapter.FeedAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        searchViewModel = new ViewModelProvider( this).get( SearchViewModel.class);

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = root.findViewById( R.id.searchView);
        recyclerView = root.findViewById(R.id.searchRecyclerView);
        recyclerView.setAdapter( new FeedAdapter( new ArrayList<>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchPostByTitle( s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return root;
    }

    private void searchPostByTitle( String title) {
        searchViewModel.getPosts( title).observe(getViewLifecycleOwner(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList posts) {
                recyclerView.setAdapter( new FeedAdapter( posts));
            }
        });
    }
}