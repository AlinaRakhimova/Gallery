package ru.rakhimova.instagramclient.model.network;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.entity.Photo;

public class PixabayApi {

    private static final String KEY_PIXABAY = "9250926-552b631cddef606bad3e807d2";

    @Inject
    PixabayService api;

    public PixabayApi() {
        App.getAppComponent().inject(this);
    }

    public Observable<Photo> requestServer() {
        return api.getPhoto(KEY_PIXABAY).subscribeOn(Schedulers.io());
    }

}
