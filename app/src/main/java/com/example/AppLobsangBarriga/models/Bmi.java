package com.example.AppLobsangBarriga.models;

// Bmi model

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bmi implements Serializable, IBmi {
    private long id;
    private Date date;
    private double weight;
    private double calculatedBmi;
    private long userId;

    public Bmi(Date date, double weight, double calculatedBmi, long userId) {
        this.date = date;
        this.weight = weight;
        this.calculatedBmi = calculatedBmi;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightAsString() {
        return Double.toString(weight);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalculatedBmi() {
        return calculatedBmi;
    }

    public String getCalculatedBmiAsString() {
        return Double.toString(calculatedBmi);
    }

    public void setCalculatedBmi(double calculatedBmi) {
        this.calculatedBmi = calculatedBmi;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
