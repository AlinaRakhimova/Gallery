package ru.rakhimova.instagramclient.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;
import ru.rakhimova.instagramclient.model.entity.Hit;

@Dao
public interface HitDao {

    @Query("SELECT * FROM table_hits")
    Single<List<Hit>> getAll();

    @Query("SELECT * FROM table_hits WHERE id = :id")
    Single<Hit> getDetailHit(int id);

    @Insert
    List<Long> insertList(List<Hit> hits);

    @Delete
    int delete(Hit hit);

    @Update
    int update(Hit hit);

}

