package ru.rakhimova.instagramclient.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private static final String FIRST_ENTER = "first_enter";
    private static final String SETTINGS = "settings";
    private SharedPreferences sharedPreferences;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SETTINGS, Activity.MODE_PRIVATE);
    }

    public boolean isFirstEnter() {
        return sharedPreferences.getBoolean(FIRST_ENTER, true);
    }

    public void setFirstEnter(boolean firstEnter) {
        sharedPreferences.edit().putBoolean(FIRST_ENTER, firstEnter).apply();
    }

}
