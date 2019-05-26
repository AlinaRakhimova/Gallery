package ru.rakhimova.instagramclient.model.database;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.entity.FavoriteHit;
import ru.rakhimova.instagramclient.model.entity.Hit;

public class RoomHelper {

    @Inject
    HitDao hitDao;

    @Inject
    FavoriteHitDao favoriteHitDao;

    public RoomHelper() {
        App.getAppComponent().inject(this);
    }

    public Single<Hit> getHit(int id) {
        return hitDao.getDetailHit(id);
    }

    public Single<List<Hit>> getFavoriteList() {
        return favoriteHitDao.getAll();
    }

    public int deleteFavoritePhoto(int idPhoto) {
        return favoriteHitDao.delete(idPhoto);
    }

    public List<Long> insertPhotoList(List<Hit> hitList) {
        return hitDao.insertList(hitList);
    }

    public Single<List<Hit>> getPhotoList() {
        return hitDao.getAll();
    }

    public Long insertFavoritePhoto(FavoriteHit favoriteHit) {
        return favoriteHitDao.insert(favoriteHit);
    }

}
