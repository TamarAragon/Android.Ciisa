package com.example.AppLobsangBarriga.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.AppLobsangBarriga.dao.UserDao;
import com.example.AppLobsangBarriga.models.UserEntity;
import com.example.AppLobsangBarriga.utils.Converters;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppLobsangBarrigaDatabase extends RoomDatabase {
    private static final String DB_NAME = "AppLobsangBarriga";
    private static AppLobsangBarrigaDatabase instance;

    public static synchronized AppLobsangBarrigaDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(), AppLobsangBarrigaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
