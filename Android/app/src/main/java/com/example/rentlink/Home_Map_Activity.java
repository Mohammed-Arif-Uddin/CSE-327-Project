package com.example.rentlink;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentlink.UtilsService.UtilService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home_Map_Activity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng selectedLatLng; // To store the selected location
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private UtilService utilService = new UtilService();

    private double radius =  0.008;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        // Set default location to a specific latitude and longitude
        LatLng defaultLocation = new LatLng(23.8103, 90.4125);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            enableMyLocation();
            addHardcodedLocations();
        }
    }

    private void enableMyLocation() {
        if (googleMap != null && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null) {
                LatLng currentLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(currentLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                selectedLatLng = currentLocation;
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                makeGetRequest(selectedLatLng.latitude, selectedLatLng.longitude, radius);
            }

            googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {
                    // Update the selectedLatLng whenever the camera moves
                    selectedLatLng = googleMap.getCameraPosition().target;

                    //makeGetRequest(selectedLatLng.latitude, selectedLatLng.longitude, radius);

                }
            });
        }
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

                                //funtion

                                LatLng location = new LatLng(lon, lat);
                                Log.d("Location", "Latitude: " + lat + ", Longitude: " + lon + ", Location Name: " + locationName);
                                googleMap.addMarker(new MarkerOptions().position(location).title(locationName));
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




    private void addHardcodedLocations() {
        // Add hardcoded locations to the map
        LatLng location1 = new LatLng(23.8103, 90.4125); // Default location
        LatLng location2 = new LatLng(23.8135, 90.4242); // Jamuna Future Park
        LatLng location3 = new LatLng(23.8116, 90.4157); // Some other location

        googleMap.addMarker(new MarkerOptions().position(location1));
        googleMap.addMarker(new MarkerOptions().position(location2));
        googleMap.addMarker(new MarkerOptions().position(location3));
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