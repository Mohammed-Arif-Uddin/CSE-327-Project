package com.example.rentlink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentlink.Model.Post;
import com.squareup.picasso.Picasso;

public class ViewPostActivity extends AppCompatActivity {

    private ImageView imageViewPost;
    private TextView textViewTitle;
    private TextView textViewRent;
    private Button buttonSendMessage;
    private TextView textViewSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        // Get the post data from the intent
        Post post = (Post) getIntent().getSerializableExtra("post");

        // Initialize views
        imageViewPost = findViewById(R.id.imageViewPost);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewRent = findViewById(R.id.textViewRent);
        textViewSize = findViewById(R.id.textViewSize);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        // Set post data to views
        if (post != null) {
            // Load post image using Picasso or any other image loading library
            Picasso.get().load(post.getImageUrl()).into(imageViewPost);

            // Set post title and description
            textViewTitle.setText(post.getApartmentName());
            textViewRent.setText("RENT: " +post.getRent());
            textViewSize.setText("SIZE: " +post.getSize());

            // Handle "Send Message" button click
            buttonSendMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the chat activity
                    Intent intent = new Intent(ViewPostActivity.this, Chat_Activity.class);
                    intent.putExtra("post", post);
                    startActivity(intent);
                }
            });
        }
    }
}
