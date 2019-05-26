package ru.rakhimova.instagramclient.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.rakhimova.instagramclient.model.entity.FavoriteHit;
import ru.rakhimova.instagramclient.model.entity.Hit;

@Dao
public interface FavoriteHitDao {

    @Query("SELECT table_hits.id, table_hits.title, table_hits.webformatURL FROM table_hits, table_favorite WHERE table_hits.id == table_favorite.idPhoto")
    Single<List<Hit>> getAll();

    @Query("DELETE FROM table_favorite WHERE table_favorite.idPhoto = :idPhoto")
    int delete(int idPhoto);

    @Insert
    Long insert(FavoriteHit hit);

}
