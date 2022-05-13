package com.example.alumniadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumniadmin.R;
import com.example.alumniadmin.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private ArrayList<User> allUsers;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView;
        CardView userCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.userImage);
            this.titleView = itemView.findViewById(R.id.userName);
            this.userCard = itemView.findViewById(R.id.userCard);
        }
    }

    public UsersAdapter(ArrayList<User> data) {
        this.allUsers = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView titleView = holder.titleView;

        titleView.setText(allUsers.get(listPosition).getName());

        Picasso.get()
                .load( allUsers.get(listPosition).getPic())
                .into( holder.imageView);
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }
}
