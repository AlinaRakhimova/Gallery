package ru.rakhimova.instagramclient.model.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.rakhimova.instagramclient.model.entity.Photo;

public interface RetrofitService {

    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);

}
