//package com.example.rentlink;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.rentlink.UtilsService.SharedPreferenceClass;
//import com.example.rentlink.UtilsService.UtilService;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private Button loginButton;
//    private Button signUpButton;
//    private EditText emailEditText;
//    private EditText passwordEditText;
//
//    private Button enterButton;
//
//    private SharedPreferenceClass sharedPreferenceClass;
//    private UtilService utilService = new UtilService();
//    //UtilService utilService = new UtilService();
//
//    //private String loginUrl = "http://192.168.1.2:3500/auth";
//    private String loginUrl = utilService.getUrl() + "auth";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        loginButton = findViewById(R.id.buttonLogin);
//        signUpButton = findViewById(R.id.buttonSignUp);
//        emailEditText = findViewById(R.id.editTextEmail);
//        passwordEditText = findViewById(R.id.editTextPassword);
//        enterButton = findViewById(R.id.buttonEnter);
//
//        sharedPreferenceClass = new SharedPreferenceClass(this);
//
//
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailEditText.getText().toString().trim();
//                String password = passwordEditText.getText().toString().trim();
//
//                if (email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
//                } else {
//                    login(email, password);
//                }
//            }
//        });
//
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to RegisterActivity
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void login(String email, String password) {
//        final JSONObject requestData = new JSONObject();
//        try {
//            requestData.put("email", email);
//            requestData.put("pwd", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, requestData,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            String userId = response.getString("userId");
//                            String accessToken = response.getString("accessToken");
//                            String firstName = response.getString("firstname");
//                            String lastName = response.getString("lastname");
//
//                            // Save the access token to use for authenticated requests
//                            // Store the access token and user ID in SharedPreferenceClass
//                            sharedPreferenceClass.setAccessToken(accessToken);
//                            sharedPreferenceClass.setUserId(userId);
//                            // You can store it in SharedPreferences or any other appropriate storage
//
//                            // Display access token and user ID in a Toast
//                            //String toastMessage = "Access Token: " + sharedPreferenceClass.getAccessToken()
//                            //        + "\nUser ID: " + sharedPreferenceClass.getUserId();
//                            //Toast.makeText(LoginActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
//
//
//                            // Navigate to MainActivity
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.putExtra("userId", userId);
//                            intent.putExtra("accessToken", accessToken);
//                            intent.putExtra("firstName", firstName);
//                            intent.putExtra("lastName", lastName);
//                            startActivity(intent);
//                            Log.e(loginUrl,"onErrorResponse: " + response.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
//                        Log.e(loginUrl,"onErrorResponse: "+error.getLocalizedMessage());
//
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                // Set any required headers for the login request
//                // For example, if your server expects JSON data, you can set the "Content-Type" header
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
//        requestQueue.add(jsonObjectRequest);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        // Check if access token exists in SharedPreferenceClass
//        String storedAccessToken = sharedPreferenceClass.getAccessToken();
//        if (!storedAccessToken.isEmpty()) {
//            // Access token found, redirect to MainActivity
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("accessToken", storedAccessToken);
//            startActivity(intent);
//            finish(); // Optional: finish LoginActivity to prevent going back
//        }
//    }
//
//    //The onStart method will be called when the LoginActivity is started or resumed. It checks if the access token exists in the SharedPreferenceClass. If an access token is found, it creates an intent to start the MainActivity and passes the access token as an extra. Finally, it starts the MainActivity and calls finish() to optionally finish the LoginActivity (preventing the user from going back to it using the back button).
//
//}
//
//
//
package com.example.rentlink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentlink.UtilsService.SharedPreferenceClass;
import com.example.rentlink.UtilsService.UtilService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signUpButton;
    private Button enterButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    private SharedPreferenceClass sharedPreferenceClass;
    private UtilService utilService = new UtilService();
    //UtilService utilService = new UtilService();

    //private String loginUrl = "http://192.168.1.2:3500/auth";
    private String loginUrl = utilService.getUrl() + "auth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.buttonLogin);
        signUpButton = findViewById(R.id.buttonSignUp);
        enterButton = findViewById(R.id.buttonEnter);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);

        sharedPreferenceClass = new SharedPreferenceClass(this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    login(email, password);
                }
            }
        });

              enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View view) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                               startActivity(intent);
                           }
       });

                signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Navigate to RegisterActivity
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void login(String email, String password) {
        final JSONObject requestData = new JSONObject();
        try {
            requestData.put("email", email);
            requestData.put("pwd", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String userId = response.getString("userId");
                            String accessToken = response.getString("accessToken");
                            String firstName = response.getString("firstname");
                            String lastName = response.getString("lastname");

                            // Save the access token to use for authenticated requests
                            // Store the access token and user ID in SharedPreferenceClass
                            sharedPreferenceClass.setAccessToken(accessToken);
                            sharedPreferenceClass.setUserId(userId);
                            // You can store it in SharedPreferences or any other appropriate storage

                            // Display access token and user ID in a Toast
                            //String toastMessage = "Access Token: " + sharedPreferenceClass.getAccessToken()
                            //        + "\nUser ID: " + sharedPreferenceClass.getUserId();
                            //Toast.makeText(LoginActivity.this, toastMessage, Toast.LENGTH_SHORT).show();


                            // Navigate to MainActivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userId", userId);
                            intent.putExtra("accessToken", accessToken);
                            intent.putExtra("firstName", firstName);
                            intent.putExtra("lastName", lastName);
                            startActivity(intent);
                            Log.e(loginUrl,"onErrorResponse: " + response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        Log.e(loginUrl,"onErrorResponse: "+error.getLocalizedMessage());

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Set any required headers for the login request
                // For example, if your server expects JSON data, you can set the "Content-Type" header
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if access token exists in SharedPreferenceClass
        String storedAccessToken = sharedPreferenceClass.getAccessToken();
        if (!storedAccessToken.isEmpty()) {
            // Access token found, redirect to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("accessToken", storedAccessToken);
            startActivity(intent);
            finish(); // Optional: finish LoginActivity to prevent going back
        }
    }

    //The onStart method will be called when the LoginActivity is started or resumed. It checks if the access token exists in the SharedPreferenceClass. If an access token is found, it creates an intent to start the MainActivity and passes the access token as an extra. Finally, it starts the MainActivity and calls finish() to optionally finish the LoginActivity (preventing the user from going back to it using the back button).

}
