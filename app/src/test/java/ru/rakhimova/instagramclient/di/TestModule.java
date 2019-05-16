package ru.rakhimova.instagramclient.di;

import android.content.Context;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import ru.rakhimova.instagramclient.UserPreferences;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.network.RetrofitApi;

@Module
public class TestModule {

    @Provides
    public RetrofitApi getRetrofitApi() {
        return Mockito.mock(RetrofitApi.class);
    }

    @Provides
    public HitDao getHitDao() {
        return Mockito.mock(HitDao.class);
    }

    @Provides
    public UserPreferences getUserPreferences(Context context) {
        return new UserPreferences(context);
    }

    @Provides
    public Context getContext() {
        return Mockito.mock(Context.class);
    }

    @Provides
    public AppComponent getAppComponent() {
        return Mockito.mock(AppComponent.class);
    }

}
