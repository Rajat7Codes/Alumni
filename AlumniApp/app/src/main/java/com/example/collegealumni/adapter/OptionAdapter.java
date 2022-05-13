package com.example.collegealumni.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.EditProfileActivity;
import com.example.collegealumni.LoginActivity;
import com.example.collegealumni.MyPostsActivity;
import com.example.collegealumni.R;
import com.example.collegealumni.model.Option;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {

    private ArrayList<Option> allOptions;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView optionImage;
        TextView optionName;
        CardView optionCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.optionImage = itemView.findViewById(R.id.optionImageView);
            this.optionName = itemView.findViewById(R.id.optionText);
            this.optionCard = itemView.findViewById(R.id.optionCard);
        }
    }

    public OptionAdapter(ArrayList<Option> data) {
        this.allOptions = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_option, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView titleView = holder.optionName;

        titleView.setText( allOptions.get(listPosition).getTitle());

        if( allOptions.get(listPosition).getImageId()==1) {
            holder.optionImage.setImageResource( R.drawable.icon_edit);
            holder.optionCard.setOnClickListener(this::editProfile);
        }

        if( allOptions.get(listPosition).getImageId()==2) {
            holder.optionImage.setImageResource( R.drawable.icon_my_posts);
            holder.optionCard.setOnClickListener(this::viewAllPost);
        }

        if( allOptions.get(listPosition).getImageId()==4) {
            holder.optionImage.setImageResource( R.drawable.icon_logout);
            holder.optionCard.setOnClickListener(this::logoutUser);
        }
    }

    @Override
    public int getItemCount() { return allOptions.size(); }

    private void editProfile( View v) {
        Intent intent = new Intent( v.getContext(), EditProfileActivity.class);
        v.getContext().startActivity( intent);
    }

    private void viewAllPost( View v) {
        Intent intent = new Intent( v.getContext(), MyPostsActivity.class);
        v.getContext().startActivity( intent);
    }

    private void logoutUser( View v) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        Intent intent = new Intent( v.getContext(), LoginActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        v.getContext().startActivity( intent);
    }
}
