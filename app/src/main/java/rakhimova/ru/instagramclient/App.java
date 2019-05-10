package rakhimova.ru.instagramclient;

import android.app.Application;
import android.arch.persistence.room.Room;

import rakhimova.ru.instagramclient.di.AppComponent;
import rakhimova.ru.instagramclient.di.AppModule;
import rakhimova.ru.instagramclient.di.DaggerAppComponent;
import rakhimova.ru.instagramclient.model.database.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;
    private static AppComponent appComponent;

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "photo_database").build();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
