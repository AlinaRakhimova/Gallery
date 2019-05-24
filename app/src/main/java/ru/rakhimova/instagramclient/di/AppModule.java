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
import ru.rakhimova.instagramclient.model.GlideLoader;
import ru.rakhimova.instagramclient.model.UserPreferences;
import ru.rakhimova.instagramclient.model.database.AppDatabase;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.model.network.PixabayApi;
import ru.rakhimova.instagramclient.model.network.PixabayService;

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
    Context getContext() {
        return context;
    }

    @Provides
    PixabayApi getPixabayApi() {
        return new PixabayApi();
    }

    @Provides
    RoomHelper getRoomHelper() {
        return new RoomHelper();
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
    RxJava2CallAdapterFactory getRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    PixabayService getRetrofitService(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(PixabayService.class);
    }

}
