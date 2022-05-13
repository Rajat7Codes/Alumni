package com.example.alumniadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumniadmin.R;
import com.example.alumniadmin.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

        private ArrayList<Post> allPosts;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView, profileImageView;
            TextView titleView, userNameView, captionView;
            CardView feedCard;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.profileImageView = itemView.findViewById(R.id.profileImageView);
                this.userNameView = itemView.findViewById(R.id.userNameView);
                this.imageView = itemView.findViewById(R.id.feedImageView);
                this.titleView = itemView.findViewById(R.id.titleView);
                this.captionView = itemView.findViewById(R.id.captionView);
                this.feedCard = itemView.findViewById(R.id.feedCard);
            }
        }

        public FeedAdapter(ArrayList<Post> data) {
            this.allPosts = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_feed, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
            TextView titleView = holder.titleView;
            TextView captionView = holder.captionView;
            TextView userNameView = holder.userNameView;

            titleView.setText(allPosts.get(listPosition).getTitle());
            captionView.setText(allPosts.get(listPosition).getCaption());
            userNameView.setText(allPosts.get(listPosition).getUser().getName());

//            holder.feedCard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent( view.getContext(), PostActivity.class);
//                    intent.putExtra("postId", allPosts.get(listPosition).getId());
//                    view.getContext().startActivity( intent);
//                }
//            });

            Picasso.get()
                    .load( allPosts.get(listPosition).getImageUrl())
                    .into( holder.imageView);

            if( allPosts.get(listPosition).getUser().getPic() != null)
                Picasso.get()
                        .load( allPosts.get(listPosition).getUser().getPic())
                        .into( holder.profileImageView);
        }

        @Override
        public int getItemCount() {
            return allPosts.size();
        }
}
