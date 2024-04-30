package com.example.rentlink.UtilsService;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceClass {
    private static final String USER_PREF = "user_todo";
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String USER_ID_KEY = "user_id";

    private SharedPreferences appShared;
    private SharedPreferences.Editor prefsEditor;

    public SharedPreferenceClass(Context context) {
        appShared = context.getSharedPreferences(USER_PREF, Activity.MODE_PRIVATE);
        this.prefsEditor = appShared.edit();
    }

    public String getAccessToken() {
        return appShared.getString(ACCESS_TOKEN_KEY, "");
    }

    public void setAccessToken(String accessToken) {
        prefsEditor.putString(ACCESS_TOKEN_KEY, accessToken).apply();
    }

    public String getUserId() {
        return appShared.getString(USER_ID_KEY, "");
    }

    public void setUserId(String userId) {
        prefsEditor.putString(USER_ID_KEY, userId).apply();
    }

    public void clear() {
        prefsEditor.clear().apply();
    }
}


/*public class SharedPreferenceClass {
    private static final String USER_PREF = "user_todo";
    private SharedPreferences appShared;
    private SharedPreferences.Editor prefsEditor;

    public SharedPreferenceClass(Context context) {
        appShared = context.getSharedPreferences(USER_PREF, Activity.MODE_PRIVATE);
        this.prefsEditor = appShared.edit();
    }

    // int
    public int getValue_int(String key) {
        return appShared.getInt(key, 0);
    }

    public void setValue_int(String key, int value) {
        prefsEditor.putInt(key, value).commit();
    }

    // string
    public String getValue_string(String key) {
        return appShared.getString(key, "");
    }

    public void setValue_string(String key, String value) {
        prefsEditor.putString(key, value).commit();
    }


    // boolean
    public boolean getValue_boolean(String key) {
        return appShared.getBoolean(key, false);
    }

    public void setValue_boolean(String key, boolean value) {
        prefsEditor.putBoolean(key, value).commit();
    }

    public void clear() {
        prefsEditor.clear().commit();
    }

}*/
