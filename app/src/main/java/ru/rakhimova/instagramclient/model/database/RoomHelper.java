package ru.rakhimova.instagramclient.model.database;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.entity.Hit;

public class RoomHelper {

    @Inject
    HitDao hitDao;

    public RoomHelper() {
        App.getAppComponent().inject(this);
    }

    public Single<Hit> getHit(int id) {
        return hitDao.getDetailHit(id);
    }
}
