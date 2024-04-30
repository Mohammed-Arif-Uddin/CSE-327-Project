package com.example.rentlink;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentlink.Adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class Chat_Activity extends AppCompatActivity {

    private TextView textViewRecipient;
    private EditText editTextMessage;
    private Button buttonSend;
    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<String> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize views
        textViewRecipient = findViewById(R.id.textViewRecipient);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);

        // Set recipient name (replace "Recipient Name" with actual recipient name)
        textViewRecipient.setText("Recipient Name");

        // Initialize chat messages list
        chatMessages = new ArrayList<>();

        // Initialize chat adapter and set it to the RecyclerView
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        // Handle "Send" button click
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Add the message to the chat messages list
                    chatMessages.add(message);
                    chatAdapter.notifyDataSetChanged();

                    // Clear the message input field
                    editTextMessage.setText("");
                }
            }
        });
    }
}
