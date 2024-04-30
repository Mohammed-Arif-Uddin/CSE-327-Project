package com.example.rentlink;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentlink.Adapter.PostAdapter;
import com.example.rentlink.AddPostActivity;
import com.example.rentlink.Chat_Activity;
import com.example.rentlink.Home_Map_Activity;
import com.example.rentlink.Model.Post;
import com.example.rentlink.ProfileActivity;
import com.example.rentlink.R;
import com.example.rentlink.SearchActivity;
import com.example.rentlink.UtilsService.SharedPreferenceClass;
import com.example.rentlink.UtilsService.UtilService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddPost;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private UtilService utilService = new UtilService();

    private BottomNavigationView bottomNavigationView;
    private SharedPreferenceClass sharedPreferenceClass;
    private EditText searchEditText;
    private ImageView searchIcon, micIcon;
    private TextView languageButton;

    private List<Post> postList;
    private String searchResult = "d";
    private Locale currentLocale = Locale.ENGLISH;

    private static final int SPEECH_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceClass = new SharedPreferenceClass(this);
        recyclerView = findViewById(R.id.recyclerView);

        fabAddPost = findViewById(R.id.fabAddPost);
        searchEditText = findViewById(R.id.searchEditText);
        searchIcon = findViewById(R.id.searchIcon);
        micIcon = findViewById(R.id.micIcon);
        languageButton = findViewById(R.id.languageButton);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        makeGetRequest();

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResult = searchEditText.getText().toString();
                makeGetRequest();
            }
        });

        micIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechToText();
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLanguage();
            }
        });

        fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        searchResult = "d";
                        makeGetRequest();
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_map:
                        startActivity(new Intent(MainActivity.this, Home_Map_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_search:
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_chat:
                        startActivity(new Intent(MainActivity.this, Chat_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        postList = new ArrayList<>();
    }

    private void makeGetRequest() {
        String apiUrl = utilService.getUrl() + "search/" + searchResult;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("API Response", response);
                            JSONArray jsonArray = new JSONArray(response);

                            postList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String locationName = jsonObject.optString("locationName");
                                int rent = jsonObject.optInt("rent");
                                int size = jsonObject.optInt("size");
                                JSONArray imgArray = jsonObject.optJSONArray("img");
                                String imageUrl = null;
                                if (imgArray != null && imgArray.length() > 0) {
                                    imageUrl = imgArray.optString(0);
                                }

                                Post post = new Post(locationName, String.valueOf(rent), String.valueOf(size));
                                if (imageUrl != null) {
                                    post.setImageUrl(imageUrl);
                                }
                                postList.add(post);
                            }
                            initializeAdapter();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API Error", error.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void initializeAdapter() {
        postAdapter = new PostAdapter(MainActivity.this, postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(postAdapter);
        postAdapter.setOnViewPostClickListener(new PostAdapter.OnViewPostClickListener() {
            @Override
            public void onViewPostClick(Post post) {
                Intent intent = new Intent(MainActivity.this, ViewPostActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
            }
        });
    }

    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, currentLocale);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && results.size() > 0) {
                String spokenText = results.get(0);
                searchEditText.setText(spokenText);
            }
        }
    }

    private void toggleLanguage() {
        if (currentLocale == Locale.ENGLISH) {
            currentLocale = new Locale("bn");
            languageButton.setText("BN");
        } else {
            currentLocale = Locale.ENGLISH;
            languageButton.setText("EN");
        }
    }
}
