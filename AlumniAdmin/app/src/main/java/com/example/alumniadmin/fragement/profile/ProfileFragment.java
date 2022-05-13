package com.example.alumniadmin.fragement.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumniadmin.R;
import com.example.alumniadmin.adapter.OptionAdapter;
import com.example.alumniadmin.model.Option;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private ImageView profilePic;
    private TextView profileName, profileBio;
    private RecyclerView profileOptionsRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileViewModel = new ViewModelProvider( this).get( ProfileViewModel.class);

        View root = inflater.inflate( R.layout.fragment_profile, container, false);

        profilePic = root.findViewById( R.id.profilePic);
        profileName = root.findViewById( R.id.profileName);
        profileBio = root.findViewById( R.id.profileBio);
        profileOptionsRecycler = root.findViewById( R.id.profileOptionList);

        ArrayList<Option> allOptions = new ArrayList<Option>();
        allOptions.add( new Option( "Edit Profile", 1));
        allOptions.add( new Option( "All Posts", 2));
        allOptions.add( new Option( "Logout", 4));

        profileOptionsRecycler.setAdapter( new OptionAdapter( allOptions));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        profileOptionsRecycler.setLayoutManager(llm);

        profileViewModel.getUser().observe(getViewLifecycleOwner(), user -> {

            if( user.getPic() != null && user.getPic().length() > 0)
                Picasso.get()
                    .load( user.getPic())
                    .placeholder(R.drawable.icon_profile)
                    .into( profilePic);

            profileName.setText( user.getName());
            profileBio.setText( user.getBio());
        });

        return root;
    }
}