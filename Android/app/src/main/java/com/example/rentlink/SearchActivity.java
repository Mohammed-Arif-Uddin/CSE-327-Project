package com.example.rentlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentlink.UtilsService.UtilService;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private UtilService utilService = new UtilService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_search);//fixes the slider animation on the selected icon


        // Set the listener for item selection
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // Handle Home item selection
                        startActivity(new Intent(SearchActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_profile:
                        // Handle Profile item selection
                        startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_map:
//                         Handle Map item selection
                        startActivity(new Intent(SearchActivity.this, Home_Map_Activity.class));
                        finish();
                        return true;
                    case R.id.navigation_search:
                        // Handle Search item selection
                        startActivity(new Intent(SearchActivity.this, SearchActivity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                    case R.id.navigation_chat:
                        // Handle Search item selection
                        startActivity(new Intent(SearchActivity.this, Chat_Activity.class));
                        overridePendingTransition(0, 0);
                        //finish();
                        return true;
                }
                return false;
            }
        });
    }

    // Add this method to your class
    private void makeGetRequest(double latitude, double longitude, double radius) {

        String apiUrl = utilService.getUrl() + "getnearby/?lat=" + "90.425492" + "&lon=" + "23.815892" + "&radius=" + radius;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("API Response", response);
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                double lat = jsonObject.getDouble("lat");
                                double lon = jsonObject.getDouble("long");
                                String locationName = jsonObject.getString("locationName");

                                LatLng location = new LatLng(lon, lat);
                                Log.d("Location", "Latitude: " + lat + ", Longitude: " + lon + ", Location Name: " + locationName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Log.e("API Error", error.toString());
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}