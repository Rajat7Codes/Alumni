package com.example.collegealumni.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealumni.EditPostActivity;
import com.example.collegealumni.R;
import com.example.collegealumni.model.Post;
import com.example.collegealumni.service.PostService;
import com.example.collegealumni.serviceIMPL.PostServiceIMPL;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyViewHolder> {

    private ArrayList<Post> allPosts;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, editPost, deletePost;
        TextView titleView;
        CardView myPostCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.myPostImageView);
            this.titleView = itemView.findViewById(R.id.myPostTitleView);
            this.myPostCard = itemView.findViewById(R.id.myPostCard);
            this.editPost = itemView.findViewById( R.id.editPost);
            this.deletePost = itemView.findViewById( R.id.deletePost);
        }
    }

    public MyPostsAdapter(ArrayList<Post> data) {
        this.allPosts = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_posts, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView titleView = holder.titleView;

        titleView.setText(allPosts.get(listPosition).getTitle());

        Picasso.get()
                .load( allPosts.get(listPosition).getImageUrl())
                .into( holder.imageView);

        holder.editPost.setOnClickListener( view -> {
            Intent intent = new Intent( view.getContext(), EditPostActivity.class);
            intent.putExtra( "postId", allPosts.get( listPosition).getId());
            view.getContext().startActivity( intent);
        });

        holder.deletePost.setOnClickListener( view -> {
            PostService postService = new PostServiceIMPL();
            postService.deletePost( isSuccessful -> {
                if( isSuccessful) {
                    Toast.makeText( view.getContext(), "Post removed successfully", Toast.LENGTH_SHORT).show();
                    allPosts.remove( listPosition);
                    notifyItemRemoved( listPosition);
                    notifyItemRangeChanged( listPosition, getItemCount());
                } else {
                    Toast.makeText(view.getContext(), "Problem occured Try Again", Toast.LENGTH_SHORT).show();
                }
            }, allPosts.get( listPosition));
        });
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }
}
