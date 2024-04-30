package com.example.rentlink;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentlink.UtilsService.SharedPreferenceClass;
import com.example.rentlink.UtilsService.UtilService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {

    private UtilService utilService = new UtilService();

    private String BASE_URL = utilService.getUrl() + "post"; // Update with your server URL

    private EditText editTextLocationName;
    private EditText editTextRentAmount;
    private EditText editTextSize;
    private EditText editTextDescription;

    // Declare the floating action buttons

    private FloatingActionButton micButton;
    private Button buttonPostAd;

    private Button btnPickLocation;//Location
    private String selectedLocation;//Location

    // SpeechRecognizer variables
    private SpeechRecognizer speechRecognizer;
    private ActivityResultLauncher<Intent> speechRecognitionLauncher;

    //private Button languageSwitchButton;
    private Locale currentLocale;
    private Intent speechRecognizerIntent;
    //private boolean isListening = false;

    // Request code for speech recognition
    private static final int SPEECH_REQUEST_CODE = 1;

    private SharedPreferenceClass sharedPreferenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //Location
        btnPickLocation = findViewById(R.id.btnPickLocation);
        btnPickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MapActivity to pick a location
                Intent intent = new Intent(AddPostActivity.this, MapActivity.class);
                startActivityForResult(intent, 1);
            }
        });//Location


        editTextLocationName = findViewById(R.id.editTextLocationName);
        editTextRentAmount = findViewById(R.id.editTextRentAmount);
        editTextSize = findViewById(R.id.editTextSize);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonPostAd = findViewById(R.id.buttonPostAd);

        //languageSwitchButton = findViewById(R.id.languageSwitchButton);
        currentLocale = Locale.ENGLISH; // Set initial language to English

        SwitchMaterial languageSwitchButton = findViewById(R.id.languageSwitchButton);
        languageSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Switch the language between English and Bengali
                if (isChecked) {
                    currentLocale = new Locale("bn"); // Bengali locale
                    languageSwitchButton.setText("BN");
                } else {
                    currentLocale = Locale.ENGLISH;
                    languageSwitchButton.setText("ENG");
                }
            }
        });


        // Initialize the floating action buttons

        micButton = findViewById(R.id.micButton1);
        sharedPreferenceClass = new SharedPreferenceClass(this);

        // Initialize SpeechRecognizer
        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, currentLocale.toString());


        speechRecognitionLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                ArrayList<String> matches = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (matches != null && !matches.isEmpty()) {
                    String spokenText = matches.get(0);
                    setEditTextValue(spokenText);
                }
            }
        });


        // Set up the RecognitionListener
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                // Called when the recognizer is ready to start listening
                //isListening = true;
            }

            @Override
            public void onBeginningOfSpeech() {
                // Called when the user starts speaking
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // Called when the sound level changes
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // Called when partial recognition results are available
            }

            @Override
            public void onEndOfSpeech() {
                // Called when the user stops speaking
                //isListening = false;
            }

            @Override
            public void onError(int error) {
                // Called when an error occurs during speech recognition
                //isListening = false;
                Toast.makeText(AddPostActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String spokenText = matches.get(0);

                    // Find the current focused text field
                    View focusedView = getCurrentFocus();

                    if (focusedView instanceof EditText) {
                        EditText editText = (EditText) focusedView;
                        editText.setText(spokenText);

                        // Find the next text field and request focus
                        View nextView = editText.focusSearch(View.FOCUS_DOWN);
                        if (nextView instanceof EditText) {
                            EditText nextEditText = (EditText) nextView;
                            nextEditText.requestFocus();

                            // Start speech recognition for the next text field
                            speechRecognizer.startListening(speechRecognizerIntent);
                        } else {
                            // All text fields are filled, perform desired action (e.g., submit the form)
                            /*buttonPostAd.performClick();*/
                            speechRecognizer.stopListening();
                        }
                    }
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // Called when partial recognition results are available
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // Called when an event related to the recognition progress occurs
            }
        });

        micButton.setOnClickListener(v -> {
            speechRecognitionLauncher.launch(speechRecognizerIntent);
        });






        buttonPostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationName = editTextLocationName.getText().toString().trim();
                String rentAmount = editTextRentAmount.getText().toString().trim();
                String size = editTextSize.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String userId = sharedPreferenceClass.getUserId(); // Retrieve userId from shared preferences


                // Create a JSON object with the post data
                JSONObject postObject = new JSONObject();
                try {
                    postObject.put("userId", userId);
                    postObject.put("locationName", locationName);
                    postObject.put("rent", Integer.parseInt(rentAmount));
                    postObject.put("size", Integer.parseInt(size));
                    postObject.put("descrp", description);
                    //postObject.put("pickLocation",)
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Send the JSON object to the API using Volley
                sendPostRequest(postObject);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            double latitude = data.getDoubleExtra("latitude", 0.0);
            double longitude = data.getDoubleExtra("longitude", 0.0);
            // Convert latitude and longitude to address
            getAddressFromLocation(latitude, longitude);
        }

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && !matches.isEmpty()) {
                String spokenText = matches.get(0);
                setEditTextValue(spokenText);
            }
        }
    }
    private void getAddressFromLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                // Retrieve the desired address details
//                String locationName = address.getLocality();
//                String postalCode = address.getPostalCode();
//                String country = address.getCountryName();
                // Concatenate the address details
                //Address address = addresses.get(0);
                // Retrieve the desired address details
                String houseNumber = address.getSubThoroughfare(); // Get the house number
                String houseName = address.getFeatureName(); // Get the house name
                String street = address.getThoroughfare(); // Get the street name
                String area = address.getSubLocality();
                String locality = address.getLocality(); // Get the locality/city
                String postalCode = address.getPostalCode(); // Get the postal code
                String country = address.getCountryName(); // Get the country name

                // Concatenate the address details
                selectedLocation=latitude+" , "+longitude;
                //selectedLocation = houseNumber + ", " + houseName + ", " + street + ", " +area + ", " + locality + ", " + postalCode + ", " + country;

//                selectedLocation = locationName + ", " + postalCode + ", " + country;
//                // Display the selected location
//                editTextLocationName.setText(selectedLocation);
//                Toast.makeText(this, selectedLocation, Toast.LENGTH_SHORT).show();
                //String selectedLocation = locationName + ", " + postalCode + ", " + country;
                // Display the selected location
                EditText editTextPickedLocation = findViewById(R.id.editTextPickedLocation);
                editTextPickedLocation.setText(selectedLocation);
                Toast.makeText(this, selectedLocation, Toast.LENGTH_SHORT).show();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setEditTextValue(String spokenText) {
        if (editTextLocationName.getText().toString().isEmpty()) {
            editTextLocationName.setText(spokenText);
        } else if (editTextRentAmount.getText().toString().isEmpty()) {
            editTextRentAmount.setText(spokenText);
        } else if (editTextSize.getText().toString().isEmpty()) {
            editTextSize.setText(spokenText);
        } else if (editTextDescription.getText().toString().isEmpty()) {
            editTextDescription.setText(spokenText);
        }
    }



    private void sendPostRequest(JSONObject postObject) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Created")) {
                            // Handle the case when the response is "Created"
                            Toast.makeText(AddPostActivity.this, "Post created successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            try {
                                // Attempt to parse the response as a JSON object
                                JSONObject jsonResponse = new JSONObject(response);
                                // Handle the JSON response
                                // ...
                            } catch (JSONException e) {
                                e.printStackTrace();
                                // Handle the case when the response is not a valid JSON object
                                Toast.makeText(AddPostActivity.this, "Failed to create post", Toast.LENGTH_SHORT).show();
                                Log.e("AddPostActivity", "Error: " + e.getMessage());
                            }
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Toast.makeText(AddPostActivity.this, "Failed to create post", Toast.LENGTH_SHORT).show();
                        Log.e("AddPostActivity", "Error: " + error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Add the authorization token to the headers
                String token = sharedPreferenceClass.getAccessToken(); // Retrieve access token from shared preferences
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return postObject.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        try {
            // Send the request
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that might occur during the request
            Toast.makeText(AddPostActivity.this, "Failed to send the post request", Toast.LENGTH_SHORT).show();
        }
    }




}



