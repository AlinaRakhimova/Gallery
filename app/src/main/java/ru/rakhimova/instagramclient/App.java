package ru.rakhimova.instagramclient;

import android.app.Application;

import ru.rakhimova.instagramclient.di.AppComponent;
import ru.rakhimova.instagramclient.di.AppModule;
import ru.rakhimova.instagramclient.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
