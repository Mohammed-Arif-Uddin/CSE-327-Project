package com.example.rentlink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentlink.Model.Post;
import com.example.rentlink.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;
    private OnItemClickListener itemClickListener;
    private OnViewPostClickListener viewPostClickListener;

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public interface OnViewPostClickListener {
        void onViewPostClick(Post post);
    }

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnViewPostClickListener(OnViewPostClickListener listener) {
        this.viewPostClickListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        Picasso.get().load(post.getImageUrl()).into(holder.imageView);
        holder.apartmentNameTextView.setText(post.getApartmentName());
        holder.rentTextView.setText(post.getRent());
        holder.sizeTextView.setText(post.getSize() + " sft");

        // Set click listener for item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(post);
                }
            }
        });

        // Set click listener for view post button
        holder.viewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPostClickListener != null) {
                    viewPostClickListener.onViewPostClick(post);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView apartmentNameTextView;
        TextView rentTextView;
        TextView sizeTextView;
        Button viewPostButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.HIFhomePic);
            apartmentNameTextView = itemView.findViewById(R.id.HIFapartmentName);
            rentTextView = itemView.findViewById(R.id.HIFrent);
            sizeTextView = itemView.findViewById(R.id.HIFrooms);
            viewPostButton = itemView.findViewById(R.id.viewPostButton);
        }
    }
}
