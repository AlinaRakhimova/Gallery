package rakhimova.ru.instagramclient.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rakhimova.ru.instagramclient.App;
import rakhimova.ru.instagramclient.UserPreferences;
import rakhimova.ru.instagramclient.model.GlideLoader;
import rakhimova.ru.instagramclient.model.database.HitDao;
import rakhimova.ru.instagramclient.model.network.RetrofitApi;

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
