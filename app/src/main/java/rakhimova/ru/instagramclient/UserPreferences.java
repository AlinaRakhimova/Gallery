package rakhimova.ru.instagramclient;

import android.app.Activity;
import android.content.SharedPreferences;

public class UserPreferences {

    private static final String FIRST_ENTER = "first_enter";
    private SharedPreferences sharedPreferences;

    public UserPreferences(Activity activity) {
        sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public boolean isFirstEnter() {
        return sharedPreferences.getBoolean(FIRST_ENTER, true);
    }

    public void setFirstEnter(boolean firstEnter) {
        sharedPreferences.edit().putBoolean(FIRST_ENTER, firstEnter).apply();
    }

}

