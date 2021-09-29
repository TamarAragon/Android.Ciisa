package com.example.AppLobsangBarriga.models;

public class Bmi {
    long id;
    String date;
    String weight;
    String calculatedBmi;

    public Bmi(String date, String weight, String calculatedBmi) {
        this.date = date;
        this.weight = weight;
        this.calculatedBmi = calculatedBmi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCalculatedBmi() {
        return calculatedBmi;
    }

    public void setCalculatedBmi(String calculatedBmi) {
        this.calculatedBmi = calculatedBmi;
    }
}
