package rakhimova.ru.instagramclient;

import android.app.Application;
import android.arch.persistence.room.Room;

import rakhimova.ru.instagramclient.model.database.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "photo_database").build();
    }

}
