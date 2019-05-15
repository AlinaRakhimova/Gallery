package ru.rakhimova.instagramclient.model.network;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.model.entity.Photo;

public class RetrofitApi {

    @Inject
    RetrofitService api;

    public Observable<Photo> requestServer() {
        return api.getPhoto("9250926-552b631cddef606bad3e807d2").subscribeOn(Schedulers.io());
    }

}
