package ru.rakhimova.instagramclient.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.rakhimova.instagramclient.UserPreferences;
import ru.rakhimova.instagramclient.model.GlideLoader;
import ru.rakhimova.instagramclient.model.database.AppDatabase;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.network.RetrofitApi;
import ru.rakhimova.instagramclient.model.network.RetrofitService;

@Module
public
class AppModule {

    private static final String BASE_URL = "https://pixabay.com";
    private static final String DATABASE_NAME = "photo_database";
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    RetrofitApi getRetrofitApi() {
        return new RetrofitApi();
    }

    @Provides
    HitDao getHitDao() {
        return getAppDatabase().hitDao();
    }

    @Provides
    UserPreferences getUserPreferences() {
        return new UserPreferences(context);
    }

    @Provides
    GlideLoader getGlideLoader() {
        return new GlideLoader(context);
    }

    @Provides
    AppDatabase getAppDatabase() {
        return Room.databaseBuilder(context,
                AppDatabase.class, DATABASE_NAME).build();
    }

    @Provides
    Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    GsonConverterFactory getGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    RetrofitService getRetrofitService(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(RetrofitService.class);
    }


}
