package com.example.alumniadmin.fragement.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumniadmin.R;
import com.example.alumniadmin.adapter.UsersAdapter;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    private UsersViewModel myPostsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myPostsViewModel = new ViewModelProvider( this).get( UsersViewModel.class);

        View root = inflater.inflate( R.layout.fragment_users, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.usersRecyclerView);
        recyclerView.setAdapter( new UsersAdapter( new ArrayList<>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        myPostsViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList posts) {
                recyclerView.setAdapter( new UsersAdapter( posts));
            }
        });

        return root;
    }
}