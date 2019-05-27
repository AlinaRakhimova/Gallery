package ru.rakhimova.instagramclient.model.network;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.entity.Photo;

import static ru.rakhimova.instagramclient.model.Constants.KEY_PIXABAY;

public class PixabayApi {

    @Inject
    PixabayService api;

    public PixabayApi() {
        App.getAppComponent().inject(this);
    }

    public Observable<Photo> requestServer() {
        return api.getPhoto(KEY_PIXABAY).subscribeOn(Schedulers.io());
    }

}
