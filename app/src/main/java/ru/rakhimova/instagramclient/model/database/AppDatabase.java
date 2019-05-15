package ru.rakhimova.instagramclient.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.rakhimova.instagramclient.model.entity.Hit;

@Database(entities = {Hit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HitDao hitDao();
}

