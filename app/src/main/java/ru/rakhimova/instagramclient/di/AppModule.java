package ru.rakhimova.instagramclient.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.rakhimova.instagramclient.App;
import ru.rakhimova.instagramclient.UserPreferences;
import ru.rakhimova.instagramclient.model.GlideLoader;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.network.RetrofitApi;

@Module
public
class AppModule {

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
        return App.getAppDatabase().hitDao();
    }

    @Provides
    UserPreferences getUserPreferences() {
        return new UserPreferences(context);
    }

    @Provides
    GlideLoader getGlideLoader() {
        return new GlideLoader(context);
    }

}
