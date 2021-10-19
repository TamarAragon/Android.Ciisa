package com.example.AppLobsangBarriga.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.AppLobsangBarriga.models.BmiEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface BmiDao {
    @Query("SELECT id, date, weight, calculatedBmi, user_id FROM bmi WHERE user_id = :userId")
    List<BmiEntity> findAll (long userId);

    @Query("SELECT id, date, weight, calculatedBmi, user_id FROM bmi WHERE user_id = :userId AND date BETWEEN :from AND :to")
    List<BmiEntity> findByRange (Date from, Date to, long userId);

    @Insert
    long insert(BmiEntity bmi);

    @Query("DELETE FROM bmi WHERE id = :id")
    void delete(long id);
}
