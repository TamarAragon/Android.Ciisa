package com.example.AppLobsangBarriga.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bmi")
public class BmiEntity implements IBmi {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "weight")
    private double weight;

    @ColumnInfo(name = "calculatedBmi")
    private double calculatedBmi;

    @ColumnInfo(name = "user_id")
    private long userId;

    public BmiEntity(long id, Date date, double weight, double calculatedBmi, long userId) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.calculatedBmi = calculatedBmi;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getCalculatedBmi() {
        return calculatedBmi;
    }

    @Override
    public long getUserId(){
        return userId;
    }
}
