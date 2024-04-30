////package com.example.rentlink;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.fragment.app.Fragment;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////
////import com.google.android.material.bottomnavigation.BottomNavigationView;
////import com.google.android.material.navigation.NavigationBarView;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////
////import com.google.android.material.bottomnavigation.BottomNavigationView;
////import com.google.android.material.navigation.NavigationBarView;
////
////public class MapActivity extends AppCompatActivity {
////
////    private BottomNavigationView bottomNavigationView;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_map);
////
////        bottomNavigationView = findViewById(R.id.bottomNavigationView);
////        bottomNavigationView.setSelectedItemId(R.id.navigation_map);//fixes the slider animation on the selected icon
////
////        // Set the listener for item selection
////        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                switch (item.getItemId()) {
////                    case R.id.navigation_home:
////                        // Handle Home item selection
////                        startActivity(new Intent(MapActivity.this, MainActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                    case R.id.navigation_profile:
////                        // Handle Profile item selection
////                        startActivity(new Intent(MapActivity.this, ProfileActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                    case R.id.navigation_map:
////                        // Handle Map item selection
////                        //startActivity(new Intent(MapActivity.this, MapActivity.class));
////                        //finish();
////                        return true;
////                    case R.id.navigation_search:
////                        // Handle Search item selection
////                        startActivity(new Intent(MapActivity.this, SearchActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                }
////                return false;
////            }
////        });
////    }
////}
//
//
//
//
//package com.example.rentlink;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.fragment.app.Fragment;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////
////import com.google.android.material.bottomnavigation.BottomNavigationView;
////import com.google.android.material.navigation.NavigationBarView;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////
////import com.google.android.material.bottomnavigation.BottomNavigationView;
////import com.google.android.material.navigation.NavigationBarView;
////
////public class MapActivity extends AppCompatActivity {
////
////    private BottomNavigationView bottomNavigationView;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_map);
////
////        bottomNavigationView = findViewById(R.id.bottomNavigationView);
////        bottomNavigationView.setSelectedItemId(R.id.navigation_map);//fixes the slider animation on the selected icon
////
////        // Set the listener for item selection
////        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                switch (item.getItemId()) {
////                    case R.id.navigation_home:
////                        // Handle Home item selection
////                        startActivity(new Intent(MapActivity.this, MainActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                    case R.id.navigation_profile:
////                        // Handle Profile item selection
////                        startActivity(new Intent(MapActivity.this, ProfileActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                    case R.id.navigation_map:
////                        // Handle Map item selection
////                        //startActivity(new Intent(MapActivity.this, MapActivity.class));
////                        //finish();
////                        return true;
////                    case R.id.navigation_search:
////                        // Handle Search item selection
////                        startActivity(new Intent(MapActivity.this, SearchActivity.class));
////                        overridePendingTransition(0, 0);
////                        //finish();
////                        return true;
////                }
////                return false;
////            }
////        });
////    }
////}
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private MapView mapView;
//    private GoogleMap googleMap;
//    private LatLng selectedLatLng; // To store the selected location
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);
//
//        Button btnSelectLocation = findViewById(R.id.btnSelectLocation);
//        btnSelectLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedLatLng != null) {
//                    // Pass the selected location back to the AddPostActivity
//                    Intent intent = new Intent();
//                    intent.putExtra("latitude", selectedLatLng.latitude);
//                    intent.putExtra("longitude", selectedLatLng.longitude);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap map) {
//        googleMap = map;
//        // Set default location to a specific latitude and longitude
//        LatLng defaultLocation = new LatLng(23.8103, 90.4125);
//        googleMap.addMarker(new MarkerOptions().position(defaultLocation));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
//        googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                // Remove previous marker
//                googleMap.clear();
//                // Add new marker at the clicked location
//                googleMap.addMarker(new MarkerOptions().position(latLng));
//                // Update the selected location
//                selectedLatLng = latLng;
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }



package com.example.rentlink;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng selectedLatLng; // To store the selected location
    private List<Location> nearbyLocations; // List to store the nearby locations fetched from the server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Button btnSelectLocation = findViewById(R.id.btnSelectLocation);
        btnSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedLatLng != null) {
                    // Pass the selected location back to the AddPostActivity
                    Intent intent = new Intent();
                    intent.putExtra("latitude", selectedLatLng.latitude);
                    intent.putExtra("longitude", selectedLatLng.longitude);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        // Set default location to a specific latitude and longitude
        LatLng defaultLocation = new LatLng(23.8103, 90.4125);
        googleMap.addMarker(new MarkerOptions().position(defaultLocation));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Remove previous marker
                googleMap.clear();
                // Add new marker at the clicked location
                googleMap.addMarker(new MarkerOptions().position(latLng));
                // Update the selected location
                selectedLatLng = latLng;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}




//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private MapView mapView;
//    private GoogleMap googleMap;
//    private LatLng selectedLatLng; // To store the selected location
//
//    private List<Location> nearbyLocations; // List to store the nearby locations fetched from the server
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);
//
//        Button btnSelectLocation = findViewById(R.id.btnSelectLocation);
//        btnSelectLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedLatLng != null) {
//                    // Pass the selected location back to the AddPostActivity
//                    Intent intent = new Intent();
//                    intent.putExtra("latitude", selectedLatLng.latitude);
//                    intent.putExtra("longitude", selectedLatLng.longitude);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            }
//        });
//
//        // Call a method to fetch the nearby locations from the server
//        fetchNearbyLocations();
//    }
//
//    private void fetchNearbyLocations() {
//        // Make an API request to the server to fetch the nearby locations
//        // You can use libraries like Retrofit or Volley to make the API request
//        // Upon receiving the response, parse the JSON data to extract the locations
//
//        // For demonstration purposes, let's create dummy nearby locations
//        nearbyLocations = new ArrayList<>();
//        nearbyLocations.add(new Location("Location 1", 23.8103, 90.4125));
//        nearbyLocations.add(new Location("Location 2", 23.8105, 90.4128));
//        nearbyLocations.add(new Location("Location 3", 23.8107, 90.4132));
//        nearbyLocations.add(new Location("Location 4", 23.8109, 90.4135));
//
//        // Once you have the list of nearby locations, you can display them on the map
//        displayNearbyLocationsOnMap();
//    }
//
//    private void displayNearbyLocationsOnMap() {
//        if (googleMap != null && nearbyLocations != null) {
//            for (Location location : nearbyLocations) {
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                googleMap.addMarker(new MarkerOptions().position(latLng).title(location.getName()));
//            }
//        }
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap map) {
//        googleMap = map;
//        // Set default location to a specific latitude and longitude
//        LatLng defaultLocation = new LatLng(23.8103, 90.4125);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
//        googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                // Remove previous marker
//                googleMap.clear();
//                // Add new marker at the clicked location
//                googleMap.addMarker(new MarkerOptions().position(latLng));
//                selectedLatLng = latLng;
//            }
//        });
//
//        // Display the nearby locations on the map
//        displayNearbyLocationsOnMap();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//}
//
