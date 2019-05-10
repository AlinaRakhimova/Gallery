package rakhimova.ru.instagramclient.model.network;

import io.reactivex.Observable;
import rakhimova.ru.instagramclient.model.entity.Photo;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);

}
