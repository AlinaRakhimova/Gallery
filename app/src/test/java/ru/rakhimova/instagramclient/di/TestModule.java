package ru.rakhimova.instagramclient.di;

import android.content.Context;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import ru.rakhimova.instagramclient.model.UserPreferences;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.model.network.PixabayApi;

@Module
public class TestModule {

    @Provides
    public PixabayApi getRetrofitApi() {
        return Mockito.mock(PixabayApi.class);
    }

    @Provides
    public HitDao getHitDao() {
        return Mockito.mock(HitDao.class);
    }

    @Provides
    public UserPreferences getUserPreferences() {
        return Mockito.mock(UserPreferences.class);
    }

    @Provides
    public Context getContext() {
        return Mockito.mock(Context.class);
    }

    @Provides
    public AppComponent getAppComponent() {
        return Mockito.mock(AppComponent.class);
    }

    @Provides
    public RoomHelper getRoomHelper() {
        return Mockito.mock(RoomHelper.class);
    }

}
