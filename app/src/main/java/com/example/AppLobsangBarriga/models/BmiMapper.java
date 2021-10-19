package com.example.AppLobsangBarriga.models;

public class BmiMapper {
    private IBmi bmi;

    public BmiMapper(IBmi bmi){
        this.bmi = bmi;
    }

    public Bmi toBase() {
        Bmi baseBmi = new Bmi(
                this.bmi.getDate(),
                this.bmi.getWeight(),
                this.bmi.getCalculatedBmi(),
                this.bmi.getUserId()
        );
        baseBmi.setId(this.bmi.getId());
        return baseBmi;
    }

    public BmiEntity toEntity() {
        return new BmiEntity(
                this.bmi.getId(),
                this.bmi.getDate(),
                this.bmi.getWeight(),
                this.bmi.getCalculatedBmi(),
                this.bmi.getUserId()
        );
    }
}
