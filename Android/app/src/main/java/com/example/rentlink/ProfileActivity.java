package com.example.rentlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rentlink.UtilsService.SharedPreferenceClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button logoutButton;
    private SharedPreferenceClass sharedPreferenceClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutButton = findViewById(R.id.buttonLogout);
        sharedPreferenceClass = new SharedPreferenceClass(this);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);//fixes the slider animation on the selected icon

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear shared preferences
                sharedPreferenceClass.clear();

                // Navigate to LoginActivity
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });//upto logout
        // Set the listener for item selection
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // Handle Home item selection
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_profile:
                        // Handle Profile item selection
                        //startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                        //finish();
                        return true;
                    case R.id.navigation_map:
                        // Handle Map item selection
                        startActivity(new Intent(ProfileActivity.this, Home_Map_Activity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_search:
                        // Handle Search item selection
                        startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_chat:
                        // Handle Search item selection
                        startActivity(new Intent(ProfileActivity.this, Chat_Activity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                }
                return false;
            }
        });
    }
}